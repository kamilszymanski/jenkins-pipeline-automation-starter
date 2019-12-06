package com.twitter.kszdev.jenkins.pipeline.callstack

trait WithExpectedCallStacksSources {

    String getExpectedCallStacksDirectory() {
        return 'src/test/resources/callstacks'
    }

    String getExpectedCallStackFileNamePath(String callStackFileName) {
        return "${getExpectedCallStacksDirectory()}/${callStackFileName}"
    }
}
