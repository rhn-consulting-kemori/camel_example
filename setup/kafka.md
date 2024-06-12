# Kafka-CLI
https://zenn.dev/gekal/articles/kafka-cli-basic-usage-and-sample
2.13-3.6.2

## Topic
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic mytopic
bin/kafka-topics.sh --describe --bootstrap-server localhost:9092 --topic mytopic
bin/kafka-topics.sh --delete --bootstrap-server localhost:9092 --topic mytopic
bin/kafka-topics.sh --list --bootstrap-server localhost:9092

## Producer
bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic mytopic
bin/kafka-verifiable-producer.sh --bootstrap-server localhost:9092 --topic mytopic --max-messages 10000
CTRL+D 

## Consumer
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic mytopic --group gekal
bin/kafka-verifiable-consumer.sh --bootstrap-server localhost:9092 --topic mytopic --group-id gekal
CTRL+C

## Consumer group
bin/kafka-consumer-groups.sh --list --bootstrap-server localhost:9092
bin/kafka-consumer-groups.sh --describe --bootstrap-server localhost:9092 --group gekal --offsets
bin/kafka-consumer-groups.sh --delete --bootstrap-server localhost:9092 --group gekal
bin/kafka-consumer-groups.sh --reset-offsets --bootstrap-server localhost:9092 --topic mytopic --group gekal --to-earliest
bin/kafka-consumer-groups.sh --delete-offsets --bootstrap-server localhost:9092 --topic mytopic --group gekal
