FROM mysql:8.0
ENV MYSQL_ROOT_USER=root
ENV MYSQL_ROOT_PASSWORD=admin
ENV MYSQL_DATABASE=covinfo
CMD ["--character-set-server=utf8mb4", "--collation-server=utf8mb4_general_ci", "--skip-character-set-client-handshake"]
# ADD ./docker/config/covinfo.sql /docker-entrypoint-initdb.d
EXPOSE 3306
