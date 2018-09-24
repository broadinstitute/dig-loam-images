FROM broadinstitute/dig-loam:python27

# all main work in root
WORKDIR /root

# https support, libcurl, python2.7
RUN apt-get update && apt-get -y install apt-transport-https libcurl4-openssl-dev

# install debian package for R 3.4
RUN echo "deb https://cloud.r-project.org/bin/linux/ubuntu xenial/" >> /etc/apt/sources.list
RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys E084DAB9
RUN apt-get update && apt-get -y install r-base

# download standard R packages from CRAN
RUN R -e 'install.packages(c("argparse", "reshape2", "ggplot2", "gridExtra", "caret", "corrplot", "gtable", "UpSetR"), repos="http://cran.us.r-project.org")'

# download packages from bioconductor
RUN R -e 'source("http://bioconductor.org/biocLite.R"); biocLite(c("GENESIS", "SNPRelate", "GWASTools", "gdsfmt"))'
