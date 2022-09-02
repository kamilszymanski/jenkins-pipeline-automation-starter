//@Library('<shared library name>') _

pipeline {
    agent any
    options {
        timestamps()
    }
    stages {
        stage('Generate data') {
            steps {
                ansiColor('xterm') {
                    executeScript 'scripts/display_progress_notice.sh'
                }
                executeScript 'scripts/generate_data.sh'
            }
            post {
                unsuccessful {
                    cleanWs()
                }
            }
        }
    }
}
