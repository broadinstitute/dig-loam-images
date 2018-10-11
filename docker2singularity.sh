#!/bin/bash -e

if [[ $(id -u) -ne 0 ]]; then
   echo "This script must be run as root" 
   exit 1
fi

DOCKER_IMAGE=${1?"Usage: $0 <docker image to convert>"}

PORT="5000"

SINGULARITY_IMAGE="${DOCKER_IMAGE}.simg"

DEF_FILE="${DOCKER_IMAGE}-singularity.def"

REGISTRY_HOST="127.0.0.1"

#
#Start a local docker registry
REGISTRY_CONTAINER_ID=$(docker run -d -p ${REGISTRY_HOST}:${PORT}:${PORT} --restart=always --name registry registry:2.6.2)

echo "Docker registry started at ${REGISTRY_HOST}:${PORT}, container id ${REGISTRY_CONTAINER_ID}"

#
#Push the Docker image there
docker tag ${DOCKER_IMAGE} "${REGISTRY_HOST}:${PORT}/${DOCKER_IMAGE}"

docker push "${REGISTRY_HOST}:${PORT}/${DOCKER_IMAGE}"

#
#Write a def file that tells Singularity to build the Singularity image off of the Docker one with no modifications

echo "Writing Singularity .def file ${DEF_FILE}"

cat >${DEF_FILE} <<EOL
Bootstrap: docker
Registry: http://${REGISTRY_HOST}:${PORT}
Namespace:
From: ${DOCKER_IMAGE}:latest
EOL

echo "Building Singularity image ${SINGULARITY_IMAGE} from Docker image ${DOCKER_IMAGE}"

SINGULARITY_NOHTTPS=1 singularity build ${SINGULARITY_IMAGE} ${DEF_FILE}

rm ${DEF_FILE}

#
#Shut down and remove the local docker registry

echo "Shutting down Docker registry container ${REGISTRY_CONTAINER_ID}"

docker stop "${REGISTRY_CONTAINER_ID}"
docker rm "${REGISTRY_CONTAINER_ID}"
