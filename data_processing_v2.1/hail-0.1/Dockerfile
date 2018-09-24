FROM broadinstitute/dig-loam:tools

# set debian frontend
ARG DEBIAN_FRONTEND=noninteractive

# all main work in root
WORKDIR /root

# update apt-get
RUN apt-get update

# upgrade pip
RUN pip install --upgrade pip

# install Java JDK
RUN wget --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie;" "http://download.oracle.com/otn-pub/java/jdk/8u181-b13/96a7b8442fe848ef90c96a2fad6ed6d1/jdk-8u181-linux-x64.tar.gz"
RUN tar -zxvf jdk-8u181-linux-x64.tar.gz

# install cmake
RUN apt-get -y install g++ cmake

# install Spark 2.0.2-hadoop2.7
RUN wget -q https://archive.apache.org/dist/spark/spark-2.0.2/spark-2.0.2-bin-hadoop2.7.tgz
RUN tar -zxvf spark-2.0.2-bin-hadoop2.7.tgz

# set JAVA_HOME and add it to PATH
ENV JAVA_HOME=/root/jdk1.8.0_181
ENV PATH=$PATH:$JAVA_HOME

# set JAVA_OPTS to use 8G heap memory
ENV JAVA_OPTS="-Xmx8G"

# set SPARK_HOME
ENV SPARK_HOME=/root/spark-2.0.2-bin-hadoop2.7

# clone hail v0.1 from Github and compile
RUN git clone --branch 0.1 https://github.com/broadinstitute/hail.git
RUN cd hail && chmod +x gradlew && ./gradlew -Dspark.version=2.0.2 shadowJar

# set HAIL_HOME and add the hail bin directory to the system path
ENV HAIL_HOME=/root/hail
ENV PATH=$PATH:$HAIL_HOME/bin/

# set PYTHONPATH and SPARK_CLASSPATH
ENV PYTHONPATH="$PYTHONPATH:$HAIL_HOME/python:$SPARK_HOME/python:`echo $SPARK_HOME/python/lib/py4j*-src.zip`"
ENV SPARK_CLASSPATH=$HAIL_HOME/build/libs/hail-all-spark.jar

# link to hail executable

RUN ln -s /root/hail/scripts/hail /usr/local/bin/hail