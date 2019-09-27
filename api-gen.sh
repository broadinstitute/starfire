#!/usr/bin/env bash

set -e

codegen_jar=~/software/swagger/swagger-codegen-cli.jar
api_dir=codegen
api_specs=api-docs.yaml

api_dir_new="${api_dir}_new"
api_dir_old="${api_dir}_old"

if [[ $(java -jar ${codegen_jar} validate -i ${api_specs} | wc -l)  -ge 2 ]]; then
  exit 1
fi

echo ${api_dir_new}

if [[ -e "${api_dir_new}" ]]; then
  rm -r ${api_dir_new}
fi

mkdir ${api_dir_new}
cp ${api_specs} ${api_dir_new}
cd ${api_dir_new}
java -jar ${codegen_jar} generate -l scala -i ${api_specs} --model-package starfire.terra.model --api-package starfire.terra.api
cd ..

if [[ -e "${api_dir_old}" ]]; then
  rm -r ${api_dir_old}
fi

if [[ -e "${api_dir}" ]]; then
  mv ${api_dir} ${api_dir_old}
fi
mv ${api_dir_new} ${api_dir}

echo "Done!"

