FROM broadinstitute/dig-loam:python27

# all main work in root
WORKDIR /root

# install wget
RUN apt-get update && \
	apt-get -y install wget zlib1g-dev bzip2 build-essential

# install new_fugue
RUN cd /usr/local/src && \
	wget -q http://www.sph.umich.edu/csg/abecasis/downloads/generic-new_fugue-2010-06-02.tar.gz && \
	tar zxvf generic-new_fugue-2010-06-02.tar.gz && \
	cd generic-new_fugue && make all && make install && cd ../ && \
	rm generic-new_fugue-2010-06-02.tar.gz

# install tabix and plink1.9
RUN apt-get update && \
	apt-get -y install plink1.9 && \
	ln -s /usr/bin/plink1.9 /usr/local/bin/plink && \
	apt-get -y install tabix && \
	ln -s /usr/bin/tabix /usr/local/bin/tabix && \
	ln -s /usr/bin/bgzip /usr/local/bin/bgzip

# install debian package for R 3.4 and gridExtra package
RUN echo "deb https://cloud.r-project.org/bin/linux/ubuntu xenial/" >> /etc/apt/sources.list && \
	apt-key adv --keyserver keyserver.ubuntu.com --recv-keys E084DAB9 && \
	apt-get update && \
	apt-get -y install r-base && \
	ln -s /usr/bin/R /usr/local/bin/R && \
	ln -s /usr/bin/Rscript /usr/local/bin/Rscript && \
	R -e 'install.packages(c("gridExtra"), repos="http://cran.us.r-project.org")'

# install locuszoom
RUN cd /usr/local/src && \
	wget -q https://statgen.sph.umich.edu/locuszoom/download/locuszoom_1.4_srconly.tgz && \
	tar zxvf locuszoom_1.4_srconly.tgz && \
	ln -s /usr/local/src/locuszoom/bin/locuszoom /usr/local/bin/locuszoom && \
	rm locuszoom_1.4_srconly.tgz

# open /root directory permissions
RUN chmod -R 777 /root
