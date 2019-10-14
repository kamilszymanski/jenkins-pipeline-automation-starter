def call(String outputFile) {
    sh "echo `date` > ${outputFile}"
}
