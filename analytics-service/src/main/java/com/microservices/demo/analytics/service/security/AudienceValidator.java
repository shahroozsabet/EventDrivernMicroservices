package com.microservices.demo.analytics.service.security;

import com.microservices.demo.config.AnalyticsServiceConfigData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Qualifier(value = "analytics-service-audience-validator")
@Component
@RequiredArgsConstructor
public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

    private final AnalyticsServiceConfigData analyticsServiceConfigData;

    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        if (jwt.getAudience().contains(analyticsServiceConfigData.getCustomAudience())) {
            return OAuth2TokenValidatorResult.success();
        } else {
            OAuth2Error audienceError =
                    new OAuth2Error("invalid_token", "The required audience " +
                                                     analyticsServiceConfigData.getCustomAudience() + " is missing.",
                            null);
            return OAuth2TokenValidatorResult.failure(audienceError);
        }
    }
}
