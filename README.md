# Running the application
- Please enter the correct credentials in twitter4j.properties file.
- Then run mvn install -DskipTests command
- Check new KafkaConsumerConfigData class, then kafka-consumer module, where we added the consumer config. Check also
  the new configuration file in config server repo, config-client-kafka_to_elastic.yml