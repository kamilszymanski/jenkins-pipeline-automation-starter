//@Library('<shared library name>') _

pipeline {
    agent any
    options {
        timestamps()
    }
    stages {
        stage('Generate data') {
            steps {
                script {
                    progressNotices.displayProgressNotice('Generating data')
                }
                generateData('generatedData.output')
            }
            post {
                unsuccessful {
                    cleanWs()
                }
            }
        }
    }
}
