pipeline {
    agent any
    options {
        timestamps()
    }
    stages {
        stage('Generate data') {
            steps {
                script {
                    if (isUnix()) {
                        ansiColor('xterm') {
                            sh "echo -e '\\e[32mGenerating data\\e[0m'"
                        }
                    } else {
                        echo 'Generating data'
                    }
                }
                script {
                    if (isUnix()) {
                        sh 'date > generatedData.output'
                    } else {
                        bat 'echo %date% > generatedData.output'
                    }
                }
            }
            post {
                unsuccessful {
                    cleanWs()
                }
            }
        }
    }
}
