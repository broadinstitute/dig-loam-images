FROM ubuntu:xenial

# all main work in root
WORKDIR /root

# install standard libraries and tools
RUN apt-get update && \
	apt-get -y install apt-utils apt-transport-https libjpeg8-dev libfreetype6-dev libpng-dev libagg-dev

# install python 2.7
RUN apt-get update && \
	apt-get -y install python2.7 python-setuptools && \
	ln -s /usr/bin/python /usr/local/bin/python

# install pip
RUN easy_install pip

# open /root directory permissions
RUN chmod -R 777 /root
