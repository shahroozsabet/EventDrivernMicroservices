# Running the application
- Please enter the correct credentials in twitter4j.properties file in twitter-to-kafka-service
  and enter your github password and url on bootstrap.yml file of config-server
- Then run mvn install -DskipTests command
- Then run docker-compose up command in docker-compose folder
- Check elastic-query-service, where we added first used uri for api versioning and then used content negotiation
  and leave it as the versioning of our service. Check it in controller class.