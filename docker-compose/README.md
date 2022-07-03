# Kafkacat

`docker run -it \
--network=host \
confluentinc/cp-kafkacat \
kafkacat -b localhost:19092 \
-L
`

`docker run -it \
--network=host \
confluentinc/cp-kafkacat \
kafkacat -b localhost:19092 \
-t twitter-topic
`

`docker run -it \
--network=host \
confluentinc/cp-kafkacat \
kafkacat -C -b localhost:19092 \
-t twitter-topic`

# Elastic

[https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html#_set_vm_max_map_count_to_at_least_262144
](https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html#_set_vm_max_map_count_to_at_least_262144)

wsl -d docker-desktop

sysctl -w vm.max_map_count=262144