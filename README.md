# Spring-HazelCast-MultiNode
This Spring application is implemented to use it in a multi node cluster. In memory hazelcast is used which is replicating across all nodes as and when a new node is spin up

# Following dependecies are added in addtion to WEB,JPA,MySQL Connector dependency
    //Enable caching in Spring boot
    <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-cache</artifactId>
		</dependency>
    //use hazel cast as caching, otherwise Spring Boot will use Concurrent hash map as caching by default
    <dependency>
			<groupId>com.hazelcast</groupId>
			<artifactId>hazelcast-all</artifactId>
			<version>3.12.11</version>
		</dependency>
    
    
# Create a database as per Employee.java entity class
# In this example we have used @Cacheable anotation, which will store the data when the object was pulled from DB for the first time. likewise we can use @CachePut and @CacheEvit for while adding data and deleting data from DB respectevely.
# I have created hazelcat.yaml file to configure this server as a multicast server, which will enable the system to scan and join the new nodes into the cluster as an when a new node is spin up for scalability. The cache will be automatically replicated across all in memory cache
 hazelcast:
  network:
    join:
      multicast:
        enabled: true
# Also I have enabled the Hazelcast's management center which will help us to monitor the nodes in the cluster. Follow the steps to download and start the management center in your local setup.
* Download the hazelcast tar/zip file and extract it
* Go into the management-center directory and run start.sh/start.bat as per your OS.
* Fire URL : http://localhost:8080/hazelcast-mancenter . It may prompt to create credential, create as you wish and login.
* Now the dash board will be empty, start a server directly from you IDE using "SERVER_PORT=8081 mvn spring-boot:run" or if the jar is builr already "java -DServer.port=8081 -jar <jar nmae>". Note that i have change the default node at run time as the default port used by hazelcast management console
* Go to the hazelcast-management center and see the new node is shown up in the dashboard
* Now spin up another node of the application on a different port "SERVER_PORT=8082 mvn spring-boot:run" or "java -DServer.port=8082 -jar <jar nmae>". Observe the console log for below entries

Members {size:2, ver:2} [
        Member [192.168.1.17]:5701 - 554b1f5e-3599-4970-93e9-4b77adce65da this
        Member [192.168.1.17]:5702 - 59f16005-e718-4ce6-9244-be4585f38192
]
This means both the nodes are connected and ready to serve in dtributed manner. Go to the Hazelcast management center and see the second node also listed.

If yes, you are ready to play.
# fire a "curl http://localhost:8081/employee/id/<id>" and observe the log of this 8081 server if the query hits the DB(show sql is enabled). It will hit for the first time. try again and see whats happening this time, second time DB should not be hit
# Now fire the same API(same id) but on the other node by "curl http://localhost:8082/employee/id/<id>" this time DB should not be hit, no sql will be priten.

if you observe the above two, which means the data has been pulled from the cache intead of DB for the subsequent queries.
