FROM ubuntu:xenial

# all main work in root
WORKDIR /root

# install standard libraries and tools
RUN apt-get update && \
	apt-get -y install wget zlib1g-dev bzip2

# install genotype harmonizer, klustakwik, plink1.9, tabix/bgzip, liftover, king, pdflatex, and ghostscript
RUN wget -q http://www.molgenis.org/downloads/GenotypeHarmonizer/GenotypeHarmonizer-1.4.20-dist.tar.gz && \
	tar zxvf GenotypeHarmonizer-1.4.20-dist.tar.gz && \
	chmod +x GenotypeHarmonizer-1.4.20-SNAPSHOT/GenotypeHarmonizer.sh && \
	ln -s GenotypeHarmonizer-1.4.20-SNAPSHOT/GenotypeHarmonizer.sh /usr/local/bin/GenotypeHarmonizer && \
	rm GenotypeHarmonizer-1.4.20-dist.tar.gz
RUN apt-get update && \
	apt-get -y install klustakwik && \
	ln -s /usr/bin/KlustaKwik /usr/local/bin/KlustaKwik
RUN apt-get update && \
	apt-get -y install plink1.9 && \
	ln -s /usr/bin/plink1.9 /usr/local/bin/plink
RUN apt-get update && \
	apt-get -y install tabix && \
	ln -s /usr/bin/tabix /usr/local/bin/tabix && \
	ln -s /usr/bin/bgzip /usr/local/bin/bgzip
RUN wget -q http://hgdownload.cse.ucsc.edu/admin/exe/linux.x86_64/liftOver && \
	chmod +x ./liftOver && \
	ln -s liftOver /usr/local/bin/liftOver
RUN wget -q http://people.virginia.edu/~wc9c/KING/executables/Linux-king212.tar.gz && \
	tar zxvf Linux-king212.tar.gz && \
	chmod +x ./king && \
	ln -s king /usr/local/bin/king && \
	rm Linux-king212.tar.gz
RUN apt-get update && \
	apt-get -y install texlive-latex-base ghostscript && \
	apt-get -y install texlive-fonts-recommended texlive-fonts-extra texlive-latex-extra && \
	ln -s /usr/bin/gs /usr/local/bin/gs && \
	ln -s /usr/bin/pdflatex /usr/local/bin/pdflatex

# open /root directory permissions
RUN chmod -R 777 /root
