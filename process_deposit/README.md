# camel_example
## Run
mvn clean spring-boot:run

# Container
mvn clean
mvn package
podman build -t demo_deposit_camel .
podman run -d --name demo_deposit_camel -p 8180:8180 demo_deposit_camel