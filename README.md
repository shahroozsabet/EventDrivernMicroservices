# Running the application
- Please enter the correct credentials in twitter4j.properties file in twitter-to-kafka-service
  and enter your github password and url on bootstrap.yml file of config-server
- Then run mvn install -DskipTests command
- Then run docker-compose up command in docker-compose folder
- Check elastic-query-web-client, where we added query controller, error handler and request response model classes