FROM ubuntu:xenial

# all main work in root
WORKDIR /root

# https support, libcurl
RUN apt-get update && \
	apt-get -y install apt-utils apt-transport-https libcurl4-openssl-dev

# install debian package for R 3.4
RUN echo "deb https://cloud.r-project.org/bin/linux/ubuntu xenial/" >> /etc/apt/sources.list && \
	apt-key adv --keyserver keyserver.ubuntu.com --recv-keys E084DAB9 && \
	apt-get update && \
	apt-get -y install r-base && \
	ln -s /usr/bin/R /usr/local/bin/R && \
	ln -s /usr/bin/Rscript /usr/local/bin/Rscript

# open /root directory permissions
RUN chmod -R 777 /root
