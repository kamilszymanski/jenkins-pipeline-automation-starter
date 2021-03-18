//@Library('<shared library name>') _

timestamps {
    node {
        stage('Generate data') {
            progressNotices.displayProgressNotice('Generating data')
            try {
                generateData('generatedData.output')
            } catch (exception) {
                cleanWs()
                throw exception
            }
        }
    }
}
