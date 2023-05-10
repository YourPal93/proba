#!/bin/bash

# Pull new changes
git pull

# Prepare jar
gradle clean
gradle build

# Ensure docker stopped
docker-compose stop

# Start new deployment
docker-compose up --build -d