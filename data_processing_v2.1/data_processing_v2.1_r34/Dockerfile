FROM broadinstitute/dig-loam:r34

# all main work in root
WORKDIR /root

# install R packages
RUN R -e 'install.packages(c("argparse", "reshape2", "ggplot2", "gridExtra", "caret", "corrplot", "gtable", "UpSetR"), repos="http://cran.us.r-project.org")' && \
	R -e 'source("http://bioconductor.org/biocLite.R"); biocLite(c("GENESIS", "SNPRelate", "GWASTools", "gdsfmt"))'

# open /root directory permissions
RUN chmod -R 777 /root
