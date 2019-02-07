FROM broadinstitute/dig-loam:python27

# all main work in root
WORKDIR /root

# install python-matplotlib
RUN apt-get update && \
	apt-get -y install python-matplotlib

# install pip and python packages
RUN pip install --upgrade pip && \
	pip install -q numpy pandas scipy matplotlib seaborn pysam ghostscript decorator==4.2.1

# open /root directory permissions
RUN chmod -R 777 /root
