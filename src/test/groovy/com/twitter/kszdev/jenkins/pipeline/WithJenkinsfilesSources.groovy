package com.twitter.kszdev.jenkins.pipeline

trait WithJenkinsfilesSources {

    String getJenkinsfilePath(String jenkinsfileName) {
        return "jenkinsfiles/${jenkinsfileName}"
    }
}
