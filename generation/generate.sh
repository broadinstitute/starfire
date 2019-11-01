swagger_codegen_jar=~/software/swagger/swagger-codegen-cli.jar
codegen_module_dir=~/git/sttp-swagger-codegen
codegen_module_jar=$codegen_module_dir/target/sttp-swagger-codegen-1.0.0.jar
temp_dir=~/tmp
codegen_target_dir=~/git/starfire/generated
swagger_file=~/git/starfire/generation/terra-api.yaml
cd $codegen_module_dir || exit
echo "Building and packaging sttp module"
mvn clean package
echo "Done with sttp module"
ls -l $codegen_module_jar
codegen_temp_dir=$(mktemp -d -t ci-$(date +%Y-%m-%d-%H-%M-%S)-XXXXXXXXXX --tmpdir=$temp_dir)
echo "Now generating code in temporary folder $codegen_temp_dir"
java -cp $swagger_codegen_jar:codegen_module_jar io.swagger.codegen.Codegen -l sttp -i $swagger_file -o $codegen_dir
echo "Done generating code, now trying to build from generated code"
cd $codegen_dir || exit
pwd
sbt compile
