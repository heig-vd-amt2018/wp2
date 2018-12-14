FROM mysql:5.7.24
MAINTAINER Ludovic Delafontaine <ludovic.delafontaine@heig-vd.ch>

ARG MYSQL_ROOT_PASSWORD

ENV MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD}

ADD scripts/ /docker-entrypoint-initdb.d/