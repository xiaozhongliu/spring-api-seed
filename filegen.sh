#!/bin/bash


# load variables from pom.xml
version=`grep "^    <version>" ./api/pom.xml | cut -c 14-27`


# init variables used behind
config="
    app_name="spring-api-seed"
    app_artifact_id="api"
    app_version=$version
    app_prod_port="9105"
"

tmpl_docker_file=`cat ./tmpl/Dockerfile`
tmpl_start=`cat ./tmpl/start.sh`


# generate deploy related files
printf "
$config\n
cat << EOF
$tmpl_docker_file
EOF
" | bash > ./Dockerfile

printf "
$config\n
cat << EOF
$tmpl_start
EOF
" | bash > ./start.sh
