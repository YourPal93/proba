#!/bin/bash

# Ensure docker compose stopped
docker-compose stop

# Ensure old application won't be deployed again
gradle clean