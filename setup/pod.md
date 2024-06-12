# Pod
podman pod create --name kafka-pod -p 2181:2181/tcp,9092:9092/tcp
podman run -d --pod=kafka-pod -e ZOOKEEPER_CLIENT_PORT="2181" -e KAFKA_OPTS="-Dlog4j.configuration=file:/etc/kafka/log4j.properties" --name=zookeeper docker.io/confluentinc/cp-zookeeper:7.4.3
podman run -d --pod kafka-pod -e KAFKA_ZOOKEEPER_CONNECT="localhost:2181" -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP="PLAINTEXT:PLAINTEXT" -e KAFKA_ADVERTISED_LISTENERS="PLAINTEXT://localhost:9092" -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR="1" -e KAFKA_CONFLUENT_LICENSE_TOPIC_REPLICATION_FACTOR="1" -e KAFKA_CONFLUENT_BALANCER_TOPIC_REPLICATION_FACTOR="1" -e KAFKA_TRANSACTION_STATE_LOG_MIN_ISR="1" -e KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR="1" -e CONFLUENT_METRICS_REPORTER_TOPIC_REPLICAS="1" -e KAFKA_OPTS="-Dlog4j.configuration=file:/etc/kafka/log4j.properties" --name kafka-broker docker.io/confluentinc/cp-kafka:7.4.3

# Network Check
https://access.redhat.com/documentation/ja-jp/red_hat_enterprise_linux/9/html/building_running_and_managing_containers/proc_communicating-between-a-container-and-an-application_assembly_communicating-among-containers
https://qiita.com/Shigai/items/d6752fd6ce034096f112

## Listen Port確認
sudo lsof -i -P | grep "LISTEN"

name1   633      ~~~~~~   10u  IPv4 0xc46fc72c3028ba51      0t0  TCP localhost:49362 (LISTEN)
name2   634      ~~~~~~   53u  IPv6 0xc46fc72c50688ca1      0t0  TCP *:3000 (LISTEN)
name3   96145    ~~~~~~   54u  IPv6 0xc46fc72c506892c1      0t0  TCP *:3306 (LISTEN)
name4   72412    ~~~~~~   82u  IPv4 0xc46fc72c4cf4e591      0t0  TCP localhost:62741 (LISTEN)

sudo kill -9 96145

## IPアドレスチェック
podman inspect --format='{{.NetworkSettings.IPAddress}}' web-container

## YAML
sudo podman generate kube confluent > ice-confluent.yaml