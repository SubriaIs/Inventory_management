# Build the Docker image for the application
build:
	docker build -t jas-rx-app .

# Start the application and database containers
up:
	docker-compose up -d

# Stop and remove the containers
down:
	docker-compose down

# Build the application and start the containers
deploy: build up


# Maven clean and package the application
mvn-clean-package:
	mvn clean package
