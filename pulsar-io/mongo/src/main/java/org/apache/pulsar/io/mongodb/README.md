## Apache Pulsar MongoDB Connector

### Usage

```yaml
version: '3.8'

services:
  mongo1:
    container_name: mongo1
    image: mongo:4.4
    restart: always
    ports:
      - "27017:27017"
    volumes:
      - ./mongo/data1:/data/db
      - ./init.sh:/scripts/init.sh
    networks:
      - mongo-network
    links:
      - mongo2
      - mongo3
    entrypoint: [ "/usr/bin/mongod", "--bind_ip_all", "--replSet", "dbrs" ]

  mongo2:
    container_name: mongo2
    image: mongo:4.4
    restart: always
    ports:
      - "27018:27017"
    volumes:
      - ./mongo/data2:/data/db
    networks:
      - mongors-network
    entrypoint: [ "/usr/bin/mongod", "--bind_ip_all", "--replSet", "dbrs" ]

  mongo3:
    container_name: mongo3
    image: mongo:4.4
    restart: always
    ports:
      - "27019:27017"
    volumes:
      - ./mongo/data3:/data/db
    networks:
      - mongo-network
    entrypoint: [ "/usr/bin/mongod", "--bind_ip_all", "--replSet", "dbrs" ]

networks:
  mongo-network:
    driver: bridge
```

```shell
#!/bin/bash

mongo <<EOF
var config = {
    "_id": "dbrs",
    "version": 1,
    "members": [
        {
            "_id": 1,
            "host": "mongo1:27017",
            "priority": 3
        },
        {
            "_id": 2,
            "host": "mongo2:27017",
            "priority": 2
        },
        {
            "_id": 3,
            "host": "mongo3:27017",
            "priority": 1
        }
    ]
};
rs.initiate(config, { force: true });
rs.status();
EOF
```

```shell
chmod +x ./init.sh
```

```shell
sudo docker-compose up -d
```

```shell
sudo docker exec mongo1 /scripts/init.sh
```
