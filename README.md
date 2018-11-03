# ProfileService
Service for managing user profile 

[![Build Status](https://travis-ci.org/Kolebnica/ProfileService.svg?branch=master)](https://travis-ci.org/Kolebnica/ProfileService)

## REST API

Default port: **8080**  

## Config server

- Run server (opisano v paren projektu)  
- Consul (dostopen na `localhost:8500`, če v nastavitvah ni navedeno drugače)


## Making & running a Docker image

1. Build Docker image with `docker build -t skiprope/profileservice . `
2. Run Docker image `docker run --name skiprope-profileservice --publish 8080:8080 --detach skiprope/profileservice`
3. Stop & remove docker container `docker stop skiprope-profileservice && docker rm skiprope-profileservice`
4. Remove docker image `docker rmi skiprope/profileservice`