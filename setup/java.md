# Java
## OpenJDK Install
sudo yum install java-17-openjdk
## Reference
* https://qiita.com/studio_meowtoon/items/4d11e94a2389758759cd
* https://kafka.apache.org/quickstart

# kamata-san sample
* https://gitlab.consulting.redhat.com/tkamata/camel-yaml-dsl-example

# Run
* mvn clean spring-boot:run -f camel_example

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