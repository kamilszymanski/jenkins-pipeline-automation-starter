def call(String outputFile) {
    sh "date > ${outputFile}"
}
