#!/bin/bash

# 验证参数
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <environment>"
    echo "Environment can be 'dev' or 'prod'"
    exit 1
fi

ENV=$1

# 设置环境变量
case $ENV in
    dev)
        DB_HOST="localhost"
        DB_PORT="3306"
        ;;
    prod)
        DB_HOST="production-db.emsp.com"
        DB_PORT="3306"
        ;;
    *)
        echo "Invalid environment. Use 'dev' or 'prod'"
        exit 1
        ;;
esac

# 构建Docker镜像
docker build -t emsp-service:$ENV .

# 运行服务
docker run -d \
    -e SPRING_PROFILES_ACTIVE=$ENV \
    -e DB_URL="jdbc:mysql://$DB_HOST:$DB_PORT/emspdb" \
    -e DB_USERNAME="emspuser" \
    -e DB_PASSWORD="emspassword" \
    -p 8080:8080 \
    --name emsp-service-$ENV \
    emsp-service:$ENV

echo "eMSP Account & Card Service deployed successfully in $ENV environment"