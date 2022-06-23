Kafkacat

docker run -it \
--network=host \
confluentinc/cp-kafkacat \
kafkacat -b localhost:19092 \
-L
