timestamps {
    node {
        stage('Generate data') {
            ansiColor('xterm') {
                sh "echo -e '\\e[32mGenerating data\\e[0m'"
            }
            try {
                sh 'date > generatedData.output'
            } catch (exception) {
                cleanWs()
                throw exception
            }
        }
    }
}
