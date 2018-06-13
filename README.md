# LoamStream Docker Images

The Loam Docker images are broken up into three images (see below for details on each).

In order to build these images, you will need to have [Docker](https://www.docker.com/) installed (the CE edition is fine).

## Building the Images

The base Docker image used for these images is `ubuntu:xenial` (16.04). The images are built - in order - one at a time, as each depends on the previous one. Once one is built once, there's no need to build it again unless the `Dockerfile` for it has been updated.

```bash
# First build the python27 image
docker build --tag broadinstitute/dig-loam:python27 --force-rm python27

# Next, build the r34 image
docker build --tag broadinstitute/dig-loam:r34 --force-rm r34

# Finally, build the tools image
docker build --tag broadinstitute/dig-loam:tools --force-rm tools
```

To see the images, run `docker images`:

```
REPOSITORY                  TAG                 IMAGE ID            ...
broadinstitute/dig-loam     universal           11873c3d0b78
broadinstitute/dig-loam     r34                 e8625624bd7d
broadinstitute/dig-loam     python27            138d26c94857
ubuntu                      xenial              0b1edfbffd27
```

To test one of the images, you need to create a container from it and start it.

```bash
# create a new container from the python image
$ docker create broadinstitute/dig-loam:python27 python -c "print('Hello, world!')"
1c55cc80825eb73e82e6615a216ebda78907eba1559ba05f330bc1620061edc5

# show the container list and that it was created
$ docker ps -a
CONTAINER ID        IMAGE                                  COMMAND                  CREATED             STATUS
1c55cc80825e        broadinstitute/dig-loam:python27       "python -c 'print('H…"   3 seconds ago       Created

# start the container, attach STDOUT to see the output
$ docker start -a 1c55cc80825e
Hello, world!

# get rid of the stopped container now that we don't need it
$ docker rm 1c55cc80825e
```

## Pushing an Image to DockerHub

Log into docker:

```bash
$ docker login
```

Push an image:

```bash
$ docker push broadinstitute/dig-loam:python27
$ docker push broadinstitute/dig-loam:r34
$ docker push broadinstitute/dig-loam:tools
```

## Sharing an Image

If you don't wish to publish the images, but would like to share one, you can use Docker to create a TAR from an image and send it (e.g. via FTP) to another to use:

```bash
docker save -o loam_tools.tar loam:tools
```

Conversely, once received, the image can be loaded using Docker by the receipient:

```bash
docker load < loam_tools.tgz
```

For more details/examples, see: [Docker save](https://docs.docker.com/engine/reference/commandline/save/) and
[Docker load](https://docs.docker.com/engine/reference/commandline/load/).

## Image: `loam:python27`

This image has Python 2.7 and all the dependencies required for the LoamStream pipeline to run. It is the root image that the other images require.

* numpy
* pandas
* scipy
* matplotlib
* seaborn
* pysam
* ghostscript
* decorator

## Image: `loam:r34`

This image is built from `loam:python27` and adds R 3.4 along with all the CRAN and Bioconductor packages required.

#### CRAN Packages

* argparse
* caret
* corrplot
* ggplot2
* gridExtra
* gtable
* reshape2
* UpSetR

#### Bioconductor Packages

* gdsfmt
* GENESIS
* GWASTools
* SNPRelate

## Image: `loam:tools`

This is the full image, build from `loam:r34` that has all the additional binaries and scripts installed that are used by the LoamStream pipeline.

#### Additional Tools/Scripts

* bcftools
* ghostscript
* htslib
* king
* klustakwik
* liftover
* locuszoom
* new_fugue
* pdflatex
* plink
* samtools

_Note: LocusZoom is installed in program-form only! It does not contain the reference genome!_
