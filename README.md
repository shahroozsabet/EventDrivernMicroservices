# Running the application
- Please enter the correct credentials in twitter4j.properties file in twitter-to-kafka-service
  and enter your github password and url on bootstrap.yml file of config-server
- Then run mvn install -DskipTests command
- Then run docker-compose up command in docker-compose folder
- Then run kafka-to-elastic-service in intelliJ
- Check the docker-compose folder, where we added elastic_cluster.yml file for elastic cluster
- Check elastic module, which includes 3 sub modules that we will complement implementation in coming lectures
- Note that in elastic module we defined sub modules, while for kafka we defined the sub module in main pom.xml file
  as an example, both will work