package com.microservices.demo.elastic.query.service.business.impl;

import com.microservices.demo.config.ElasticQueryServiceConfigData;
import com.microservices.demo.elastic.model.index.impl.TwitterIndexModel;
import com.microservices.demo.elastic.query.client.service.ElasticQueryClient;
import com.microservices.demo.elastic.query.service.QueryType;
import com.microservices.demo.elastic.query.service.business.ElasticQueryService;
import com.microservices.demo.elastic.query.service.common.exception.ElasticQueryServiceException;
import com.microservices.demo.elastic.query.service.common.model.ElasticQueryServiceResponseModel;
import com.microservices.demo.elastic.query.service.model.ElasticQueryServiceAnalyticsResponseModel;
import com.microservices.demo.elastic.query.service.model.ElasticQueryServiceWordCountResponseModel;
import com.microservices.demo.elastic.query.service.model.assembler.ElasticQueryServiceResponseModelAssembler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class TwitterElasticQueryService implements ElasticQueryService {

    private final ElasticQueryServiceResponseModelAssembler elasticQueryServiceResponseModelAssembler;

    private final ElasticQueryClient<TwitterIndexModel> elasticQueryClient;

    private final ElasticQueryServiceConfigData elasticQueryServiceConfigData;

    private final WebClient.Builder webClientBuilder;

    public TwitterElasticQueryService(ElasticQueryServiceResponseModelAssembler assembler,
                                      ElasticQueryClient<TwitterIndexModel> queryClient,
                                      ElasticQueryServiceConfigData queryServiceConfigData,
                                      @Qualifier("webClientBuilder")
                                      WebClient.Builder clientBuilder) {
        this.elasticQueryServiceResponseModelAssembler = assembler;
        this.elasticQueryClient = queryClient;
        this.elasticQueryServiceConfigData = queryServiceConfigData;
        this.webClientBuilder = clientBuilder;
    }

    @Override
    public ElasticQueryServiceResponseModel getDocumentById(String id) {
        log.info("Querying elasticsearch by id {}", id);
        return elasticQueryServiceResponseModelAssembler.toModel(elasticQueryClient.getIndexModelById(id));
    }

    @Override
    public ElasticQueryServiceAnalyticsResponseModel getDocumentByText(String text, String accessToken) {
        log.info("Querying elasticsearch by text {}", text);
        List<ElasticQueryServiceResponseModel> elasticQueryServiceResponseModels =
                elasticQueryServiceResponseModelAssembler.toModels(elasticQueryClient.getIndexModelByText(text));
        return ElasticQueryServiceAnalyticsResponseModel.builder()
                .queryResponseModels(elasticQueryServiceResponseModels)
                .wordCount(getWordCount(text, accessToken))
                .build();
    }

    @Override
    public List<ElasticQueryServiceResponseModel> getAllDocuments() {
        log.info("Querying all documents in elasticsearch");
        return elasticQueryServiceResponseModelAssembler.toModels(elasticQueryClient.getAllIndexModels());
    }

    private Long getWordCount(String text, String accessToken) {
        if (QueryType.KAFKA_STATE_STORE.getType().equals(elasticQueryServiceConfigData.getWebClient().getQueryType())) {
            return getFromKafkaStateStore(text, accessToken).getWordCount();
        } else if (QueryType.ANALYTICS_DATABASE.getType().
                equals(elasticQueryServiceConfigData.getWebClient().getQueryType())) {
            return getFromAnayticsDatabase(text, accessToken).getWordCount();
        }
        return 0L;
    }

    private ElasticQueryServiceWordCountResponseModel getFromAnayticsDatabase(String text, String accessToken) {
        ElasticQueryServiceConfigData.Query queryFromAnalyticsDatabase =
                elasticQueryServiceConfigData.getQueryFromAnalyticsDatabase();
        return retrieveResponseModel(text, accessToken, queryFromAnalyticsDatabase);
    }

    private ElasticQueryServiceWordCountResponseModel getFromKafkaStateStore(String text, String accessToken) {
        ElasticQueryServiceConfigData.Query queryFromKafkaStateStore =
                elasticQueryServiceConfigData.getQueryFromKafkaStateStore();
        return retrieveResponseModel(text, accessToken, queryFromKafkaStateStore);
    }

    private ElasticQueryServiceWordCountResponseModel retrieveResponseModel(String text,
                                                                            String accessToken,
                                                                            ElasticQueryServiceConfigData.Query query) {
        return webClientBuilder
                .build()
                .method(HttpMethod.valueOf(query.getMethod()))
                .uri(query.getUri(), uriBuilder -> uriBuilder.build(text))
                .headers(h -> h.setBearerAuth(accessToken))
                .accept(MediaType.valueOf(query.getAccept()))
                .retrieve()
                .onStatus(
                        s -> s.equals(HttpStatus.UNAUTHORIZED),
                        clientResponse -> Mono.just(new BadCredentialsException("Not authenticated")))
                .onStatus(
                        HttpStatus::is4xxClientError,
                        clientResponse -> Mono.just(new
                                ElasticQueryServiceException(clientResponse.statusCode().getReasonPhrase())))
                .onStatus(
                        HttpStatus::is5xxServerError,
                        clientResponse -> Mono.just(new Exception(clientResponse.statusCode().getReasonPhrase())))
                .bodyToMono(ElasticQueryServiceWordCountResponseModel.class)
                .log()
                .block();

    }
}
