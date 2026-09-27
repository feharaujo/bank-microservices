
docker run -d \
  --name accountsdb \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=accountsdb \
  -p 3306:3306 \
  mysql:latest

docker run -d \
  --name cardsdb \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=cardsdb \
  -p 3308:3306 \
  mysql:latest

docker run -d \
  --name loansdb \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=loansdb \
  -p 3307:3306 \
  mysql:latest

docker run -d \
  --name redis \
  -p 6379:6379 \
  --health-cmd="redis-cli ping" \
  --health-timeout=10s \
  --health-retries=10 \
  redis
