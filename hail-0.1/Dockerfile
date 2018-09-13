FROM broadinstitute/dig-loam:tools

# all main work in root
WORKDIR /root

# install Java JDK
RUN wget --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie;" "http://download.oracle.com/otn-pub/java/jdk/8u181-b13/96a7b8442fe848ef90c96a2fad6ed6d1/jdk-8u181-linux-x64.tar.gz"
RUN tar -zxvf jdk-8u181-linux-x64.tar.gz

# install Spark 2.0.2-hadoop2.7
RUN wget -q https://archive.apache.org/dist/spark/spark-2.0.2/spark-2.0.2-bin-hadoop2.7.tgz
RUN tar -zxvf spark-2.0.2-bin-hadoop2.7.tgz

# install Hail 0.1 precompiled version for Spark 2.0.2
RUN wget -q https://storage.googleapis.com/hail-common/distributions/0.1/Hail-0.1-a22cd8274a2c-Spark-2.0.2.zip
RUN unzip Hail-0.1-a22cd8274a2c-Spark-2.0.2.zip

# set JAVA_HOME and add it to PATH
ENV JAVA_HOME=jdk-8u181-linux-x64
ENV PATH=$PATH:$JAVA_HOME

# set JAVA_OPTS to use 8G heap memory
ENV JAVA_OPTS="-Xmx8G"

# set SPARK_HOME
ENV SPARK_HOME=spark-2.0.2-bin-hadoop2.7

# set HAIL_HOME and add the hail bin directory to the system path
ENV HAIL_HOME=hail
ENV PATH=$PATH:$HAIL_HOME/bin/
