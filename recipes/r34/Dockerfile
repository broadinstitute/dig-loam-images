FROM ubuntu:xenial

# all main work in work
WORKDIR /work

# https support, libcurl, locales
RUN apt-get update && \
	apt-get -y install apt-utils apt-transport-https libcurl4-openssl-dev locales

# Set the locale
RUN sed -i -e 's/# en_US.UTF-8 UTF-8/en_US.UTF-8 UTF-8/' /etc/locale.gen && locale-gen
ENV LANG=en_US.UTF-8  
ENV LANGUAGE=en_US:en  
ENV LC_ALL=en_US.UTF-8     

# install debian package for R 3.4
RUN echo "deb https://cloud.r-project.org/bin/linux/ubuntu xenial/" >> /etc/apt/sources.list && \
	apt-key adv --keyserver keyserver.ubuntu.com --recv-keys E084DAB9 && \
	apt-get update && \
	apt-get -y install r-base && \
	ln -s /usr/bin/R /usr/local/bin/R && \
	ln -s /usr/bin/Rscript /usr/local/bin/Rscript

# open /work directory permissions
RUN chmod -R 777 /work
