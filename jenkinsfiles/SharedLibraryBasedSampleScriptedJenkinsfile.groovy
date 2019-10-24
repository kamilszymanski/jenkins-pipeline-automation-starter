timestamps {
    node {
        stage('Generate data') {
            progressNotices.displayProgressNotice('Generating data')
            try {
                generateData('data')
            } catch (exception) {
                cleanWs()
                throw exception
            }
        }
    }
}
