# ProfileService
Service for managing user profile 

[![Build Status](https://travis-ci.org/Kolebnica/ProfileService.svg?branch=master)](https://travis-ci.org/Kolebnica/ProfileService)

## REST API

Default port: **8081**

Accessing OpenAPI: [localhost:8081/api-specs/ui/?url=http://localhost:8081/api-specs/api/openapi.json](localhost:8081/api-specs/ui/?url=http://localhost:8081/api-specs/api/openapi.json)

## Config server

- Run server (opisano v parent projektu)  
- etcd (dostopen na `localhost:2379`, če v nastavitvah ni navedeno drugače)

## Configurations and settings with etcd

Dodaj new key/value (primer za show-email):
- namespace (izpiše v konzoli): `environments/dev/services/profileservice/1.0.0/config/` +  
- sledi bundle name: `skiprope-configs/` +  
- na koncu še ime spremenljivke: `show-email` 

Cela pot je tako: `environments/dev/services/profileservice/1.0.0/config/skiprope-configs/show-email` 

## Making & running a Docker image

1. Build Docker image with `docker build -t skiprope/profileservice . `
2. Run Docker image `docker run --name skiprope-profileservice --publish 8081:8081 --detach skiprope/profileservice`
3. Stop & remove docker container `docker stop skiprope-profileservice && docker rm skiprope-profileservice`
4. Remove docker image `docker rmi skiprope/profileservice`