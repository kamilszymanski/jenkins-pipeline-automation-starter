def displayProgressNotice(String notice) {
    ansiColor('xterm') {
        sh "echo -e '\\e[32m${notice}\\e[0m'"
    }
}
