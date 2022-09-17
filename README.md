# ServiceBox

### Create Docker image
1. Go to the directory where the Dockerfile is
2. Run command `docker build -t servicebox .` to create a docker image

### Run database container
```
docker run --name sb_mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=servicebox -e MYSQL_USER=sbuser -e MYSQL_PASSWORD=password -p 3307:3306 -d mysql:8.0
```
### Run docker container and application
1. Run command `docker run -d -p 8081:8080 --name servicebox servicebox`
2. In your web browser go to `http://localhost:8081/`

### Run all with docker-compose
* First run may take longer time.
* Application will be available at `http://localhost:8081/`
```
# run in background
docker-compose up -d

# if you want to rebuild docker image
docker-compose up -d --build
```
