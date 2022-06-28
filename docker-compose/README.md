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