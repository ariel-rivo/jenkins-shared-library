import libs.awsLib

def call() {
    pipeline {
        agent any
        stages {
            stage('Hello') {
                steps {
                    script {
                        def stsClient = awsUtils().createStsClient('eu-central-1')
                        println "STS Client created successfully: ${stsClient}"                }
                }
            }
        }
    }
}
