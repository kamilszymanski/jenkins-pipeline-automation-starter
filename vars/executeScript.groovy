def call(String scriptFile, String arguments = '') {
    String script = libraryResource scriptFile
    writeFile file: scriptFile, text: script
    sh "chmod a+x ${scriptFile}"
    sh "./${scriptFile} ${arguments}"
}
