timestamps {
    node {
        stage('Sample') {
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
