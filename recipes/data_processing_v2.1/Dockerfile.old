FROM ubuntu:xenial

# all main work in root
WORKDIR /root

# update apt-get
RUN apt-get update

# install python 2.7 in the image
RUN apt-get -y install apt-utils apt-transport-https libjpeg8-dev libfreetype6-dev libpng-dev libagg-dev
RUN apt-get install -y python2.7 python-setuptools python-matplotlib
RUN easy_install pip 

# upgrade pip
RUN pip install --upgrade pip

# install packages
RUN pip install -q numpy
RUN pip install -q pandas
RUN pip install -q scipy
RUN pip install -q matplotlib
RUN pip install -q seaborn
RUN pip install -q pysam
RUN pip install -q ghostscript
RUN pip install -q decorator==4.2.1

# https support, libcurl
RUN apt-get -y install apt-transport-https libcurl4-openssl-dev

# install debian package for R 3.4
RUN echo "deb https://cloud.r-project.org/bin/linux/ubuntu xenial/" >> /etc/apt/sources.list
RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys E084DAB9
RUN apt-get -y install r-base

# download standard R packages from CRAN
RUN R -e 'install.packages(c("argparse", "reshape2", "ggplot2", "gridExtra", "caret", "corrplot", "gtable", "UpSetR"), repos="http://cran.us.r-project.org")'

# download packages from bioconductor
RUN R -e 'source("http://bioconductor.org/biocLite.R"); biocLite(c("GENESIS", "SNPRelate", "GWASTools", "gdsfmt"))'

# install wget for downloading additional packages and zlib
RUN apt-get -y install wget zlib1g-dev git bzip2

# install pdflatex
RUN apt-get -y install texlive-latex-base
# RUN apt-get -y install texlive-fonts-recommended texlive-fonts-extra texlive-latex-extra

# install ghostscript
RUN apt-get -y install ghostscript

# install klustakwik
RUN apt-get -y install klustakwik

# install plink
RUN apt-get -y install plink
RUN ln -s /usr/local/bin/snplink /usr/local/bin/plink

# install new_fugue
RUN wget -q http://www.sph.umich.edu/csg/abecasis/downloads/generic-new_fugue-2010-06-02.tar.gz
RUN tar zxvf generic-new_fugue-2010-06-02.tar.gz
RUN cd generic-new_fugue && make all && make install

# install bcftools
RUN wget -q https://github.com/samtools/bcftools/releases/download/1.8/bcftools-1.8.tar.bz2
RUN bzip2 -d bcftools-1.8.tar.bz2
RUN tar xvf bcftools-1.8.tar
RUN cd bcftools-1.8 && autoheader && autoconf -Wno-syntax && ./configure && make && make install

# install htslib
RUN wget -q https://github.com/samtools/htslib/releases/download/1.8/htslib-1.8.tar.bz2
RUN bzip2 -d htslib-1.8.tar.bz2
RUN tar xvf htslib-1.8.tar
RUN cd htslib-1.8 && autoheader && autoconf -Wno-syntax && ./configure && make && make install

# install samtools
RUN wget -q https://github.com/samtools/samtools/releases/download/1.8/samtools-1.8.tar.bz2
RUN bzip2 -d samtools-1.8.tar.bz2
RUN tar xvf samtools-1.8.tar
RUN cd samtools-1.8 && autoheader && autoconf -Wno-syntax && ./configure && make && make install

# install locuszoom (without the HG database)
RUN wget -q https://statgen.sph.umich.edu/locuszoom/download/locuszoom_1.4_srconly.tgz
RUN tar zxvf locuszoom_1.4_srconly.tgz
RUN ln -s bin/locuszoom /usr/local/bin/locuszoom

# install liftover
RUN wget -q http://hgdownload.cse.ucsc.edu/admin/exe/linux.x86_64/liftOver
RUN chmod +x ./liftOver
RUN ln -s liftOver /usr/local/bin/liftOver

# install king
RUN wget -q http://people.virginia.edu/~wc9c/KING/executables/Linux-king212.tar.gz
RUN tar zxvf Linux-king212.tar.gz
RUN chmod +x ./king
RUN ln -s king /usr/local/bin/king

# install Java JDK
RUN apt-get -y install software-properties-common
RUN add-apt-repository ppa:openjdk-r/ppa  
RUN apt-get update   
RUN apt-get -y install openjdk-8-jdk

# install cmake
RUN apt-get -y install g++ cmake

# install Spark 2.0.2-hadoop2.7
RUN wget -q https://archive.apache.org/dist/spark/spark-2.0.2/spark-2.0.2-bin-hadoop2.7.tgz
RUN tar -zxvf spark-2.0.2-bin-hadoop2.7.tgz

# set JAVA_HOME and add it to PATH
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV PATH=$PATH:$JAVA_HOME

# set JAVA_OPTS to use 8G heap memory
ENV JAVA_OPTS="-Xmx8G"

# set SPARK_HOME
ENV SPARK_HOME=/root/spark-2.0.2-bin-hadoop2.7

# clone hail v0.1 from Github and compile
RUN git clone --branch 0.1 https://github.com/broadinstitute/hail.git
RUN cd hail && chmod +x gradlew && ./gradlew -Dspark.version=2.0.2 shadowJar

# set HAIL_HOME and add the hail bin directory to the system path
ENV HAIL_HOME=/root/hail/
ENV PATH=$PATH:$HAIL_HOME/scripts/

# open /root directory permissions
RUN chmod -R 777 /root

# set PYTHONPATH and SPARK_CLASSPATH
ENV PYTHONPATH="$PYTHONPATH:$HAIL_HOME/python:$SPARK_HOME/python:`echo $SPARK_HOME/python/lib/py4j*-src.zip`"
ENV SPARK_CLASSPATH=$HAIL_HOME/build/libs/hail-all-spark.jar
