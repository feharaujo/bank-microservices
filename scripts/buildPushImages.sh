### Build and Push to docker hub
# Define the variable at the beginning
TAG=s11

cd ..

cd accounts
mvn install
mvn compile jib:dockerBuild
docker image push docker.io/fearaujo/accounts:$TAG

cd ..

cd cards
mvn install
mvn compile jib:dockerBuild
docker image push docker.io/fearaujo/cards:$TAG

cd ..

cd loans
mvn install
mvn compile jib:dockerBuild
docker image push docker.io/fearaujo/loans:$TAG

cd ..

cd configserver
mvn install
mvn compile jib:dockerBuild
docker image push docker.io/fearaujo/configserver:$TAG

cd ..

cd eurekaserver
mvn install
mvn compile jib:dockerBuild
docker image push docker.io/fearaujo/eurekaserver:$TAG

cd ..

cd gatewayserver
mvn install
mvn compile jib:dockerBuild
docker image push docker.io/fearaujo/gatewayserver:$TAG
