# ServiceBox

Application will be available at `http://localhost:8081/`

### Database container
```bash
# run database container
docker run --name servicebox_db \
 -e MYSQL_ROOT_PASSWORD=rootPassword \
 -e MYSQL_DATABASE=servicebox \
 -e MYSQL_USER=sb_user \
 -e MYSQL_PASSWORD=password \
 -p 3307:3306 \
 -d mysql:8
```

### Run application with docker-compose
```bash
# create and run containers
docker-compose up -d

# if you want to rebuild docker image
docker-compose up -d --build

# stop containers
docker-compose stop

# start containers
docker-compose start

# stop and remove containers
docker-compose down
```