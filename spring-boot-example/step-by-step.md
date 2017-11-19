1) create Maven project
   - add a soure for resources (properties > build path > add folder "resources" inside "main" folder) 
   - create application.properties (why application.yml is not being loaded)
      - change port -> server.port=8081

2) include spring boot libraries in pom.xml
   <parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.4.1.RELEASE</version>
		<relativePath/>
   </parent>

3) Set project properties
   <properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
   </properties>
   
4) Set dependencies

	// makes the execution to stay running after execution
	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
	</dependency>

5) Configure builder

   <build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
	
6) create the start class
   @SpringBootApplication
   public class Starter {
      
   public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);
   }
   
7) Define controllers
   - package.controllers
   - class annotated with @RestController (rest WS)
   - ? Changing the base URI - https://docs.spring.io/spring-data/rest/docs/current/reference/html/#_changing_the_base_uri
   