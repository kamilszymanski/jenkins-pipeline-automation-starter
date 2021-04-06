def call(String dataOutputFile, String dataGenerationProgressNotice = 'Generating data') {
    pipeline {
        agent any
        options {
            timestamps()
        }
        stages {
            stage('Generate data') {
                steps {
                    script {
                        progressNotices.displayProgressNotice(dataGenerationProgressNotice)
                    }
                    generateData(dataOutputFile)
                }
                post {
                    unsuccessful {
                        cleanWs()
                    }
                }
            }
        }
    }
}
