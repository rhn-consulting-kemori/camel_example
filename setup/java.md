# Java
## OpenJDK Install
sudo yum install java-17-openjdk
## Reference
* https://qiita.com/studio_meowtoon/items/4d11e94a2389758759cd
* https://kafka.apache.org/quickstart

# kamata-san sample
* https://gitlab.consulting.redhat.com/tkamata/camel-yaml-dsl-example
* https://gitlab.consulting.redhat.com/docomo-mobills/poc-apps

# REST sample
* https://github.com/monodot/camel-demos/blob/master/examples/spring-boot/call-rest-service/src/main/java/xyz/tomd/cameldemos/springboot/callrest/CamelCallRestRouteBuilder.java
* https://github.com/monodot/camel-demos/tree/master/examples/spring-boot/rest-service
* https://b1san-blog.com/post/spring/spring-rest-template/
* https://zenn.dev/sugaryo/books/spring-boot-run-up/viewer/api_call
* https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html

# Run
* mvn clean spring-boot:run -f camel_example
* mvn clean spring-boot:run

# Build Image
* mvn clean
* mvn package
* podman build -t camel-example .

# mvn option 
* mvn compile
* mvn test
* mvn package
* mvn install
* mvn deploy
* mvn clean