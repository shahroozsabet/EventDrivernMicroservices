# Running the application
- Please enter the correct credentials in twitter4j.properties file in twitter-to-kafka-service
  and enter your github password and url on bootstrap.yml file of config-server
- Then run mvn install -DskipTests command
- Then run docker-compose up command in docker-compose folder
- Check kafka-streams-service, which is currently a base spring boot project. We will complete the streaming in coming
  lecture