aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin "$DOCKER_ECR_REPO_URL"
./gradlew clean dockerBuildNative
docker tag platform:latest "$DOCKER_ECR_REPO_URL"/net.miiingle.files.platform:latest
docker push "$DOCKER_ECR_REPO_URL"/net.miiingle.files.platform:latest