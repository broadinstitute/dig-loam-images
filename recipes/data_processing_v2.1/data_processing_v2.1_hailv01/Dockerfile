FROM broadinstitute/dig-loam:data_processing_v2.1_python27

# all main work in work
WORKDIR /work

# install Java JDK
RUN apt-get update && \
	apt-get -y install software-properties-common && \
	add-apt-repository -y ppa:openjdk-r/ppa && \
	apt-get update && \
	apt-get -y install openjdk-8-jdk

# install g++ and cmake
RUN apt-get update && \
	apt-get -y install g++ cmake wget unzip

# install Spark 2.0.2-hadoop2.7
RUN wget -q https://archive.apache.org/dist/spark/spark-2.0.2/spark-2.0.2-bin-hadoop2.7.tgz && \
	tar -zxvf spark-2.0.2-bin-hadoop2.7.tgz && \
	rm spark-2.0.2-bin-hadoop2.7.tgz

# set JAVA_HOME and add it to PATH
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV PATH=$PATH:$JAVA_HOME

# set JAVA_OPTS to use 8G heap memory
ENV JAVA_OPTS="-Xmx8G"

# set SPARK_HOME
ENV SPARK_HOME=/work/spark-2.0.2-bin-hadoop2.7

# install hail v0.1 for spark 2.0.2 from current distribution
RUN wget https://storage.googleapis.com/hail-common/distributions/0.1/Hail-0.1-5a6778710097-Spark-2.0.2.zip && \
	unzip Hail-0.1-5a6778710097-Spark-2.0.2.zip

# set HAIL_HOME and add the hail bin directory to the system path
ENV HAIL_HOME=/work/hail/
ENV PATH=$PATH:$HAIL_HOME/bin/

# open /work directory permissions
RUN chmod -R 777 /work
