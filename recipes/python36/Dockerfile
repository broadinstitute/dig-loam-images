FROM ubuntu:xenial

# all main work in root
WORKDIR /root

# install standard libraries and tools
RUN apt-get update && \
	apt-get -y install apt-utils apt-transport-https libjpeg8-dev libfreetype6-dev libpng-dev libagg-dev software-properties-common

# install python 3.6 with pip
RUN add-apt-repository -y ppa:deadsnakes/ppa && \
	apt-get update && \
	apt-get -y install python3.6 python3-setuptools python3-pip && \
	ln -s /usr/bin/python3.6 /usr/local/bin/python && \
	ln -s /usr/bin/pip3 /usr/local/bin/pip

# open /root directory permissions
RUN chmod -R 777 /root
