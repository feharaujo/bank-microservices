### Build and Push to docker hub
# Define the variable at the beginning
TAG=s8

cd accounts
mvn compile jib:dockerBuild
docker image push docker.io/fearaujo/accounts:$TAG

cd ..

cd cards
mvn compile jib:dockerBuild
docker image push docker.io/fearaujo/cards:$TAG

cd ..

cd loans
mvn compile jib:dockerBuild
docker image push docker.io/fearaujo/loans:$TAG

cd ..

cd configserver
mvn compile jib:dockerBuild
docker image push docker.io/fearaujo/configserver:$TAG

cd ..

cd eurekaserver
mvn compile jib:dockerBuild
docker image push docker.io/fearaujo/eurekaserver:$TAG