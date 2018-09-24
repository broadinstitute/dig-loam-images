FROM broadinstitute/dig-loam:r34

# all main work in root
WORKDIR /root

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

