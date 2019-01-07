FROM mysql:5.7.24
MAINTAINER Ludovic Delafontaine <ludovic.delafontaine@heig-vd.ch>

ENV MYSQL_ROOT_PASSWORD=${DB_PASSWORD}

ADD scripts/ /docker-entrypoint-initdb.d/