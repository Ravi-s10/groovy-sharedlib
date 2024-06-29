def call(String imageName, String containerName, String command='') {
    pipeline {
        agent any
        stages {
            stage('Run Docker Container') {
                steps {
                    script {
                        // pull docker image from dockerhub
                        sh "docker pull ${imageName}"

                        // run docker container
                        if (command) {
                            sh "docker run --name ${containerName} ${imageName} ${command}"
                        }
                        else {
                            sh "docker run --name ${containerName} ${imageName}"
                        }
                    }
                }
            }
        }
        post {
            always {
                sh "docker rm -f ${containerName}"
            }
        }
    }
}
