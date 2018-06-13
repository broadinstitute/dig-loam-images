FROM ubuntu:xenial

# all main work in root
WORKDIR /root

# install python 3.4 in the image
RUN apt-get update && apt-get -y install apt-utils apt-transport-https libjpeg8-dev libfreetype6-dev libpng-dev libagg-dev
RUN apt-get install -y python2.7 python-setuptools python-matplotlib
RUN easy_install pip 

# install pacakges
RUN pip install -q numpy
RUN pip install -q pandas
RUN pip install -q scipy
RUN pip install -q matplotlib
RUN pip install -q seaborn
RUN pip install -q pysam
RUN pip install -q ghostscript
RUN pip install -q decorator
