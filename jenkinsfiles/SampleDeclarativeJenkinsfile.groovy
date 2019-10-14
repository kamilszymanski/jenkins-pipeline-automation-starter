pipeline {
    agent any
    options {
        timestamps()
    }
    stages {
        stage('Sample') {
            steps {
                ansiColor('xterm') {
                    sh "echo -e '\\e[32mGenerating data\\e[0m'"
                }
                sh 'echo `date` > data'
            }
            post {
                unsuccessful {
                    cleanWs()
                }
            }
        }
    }
}
