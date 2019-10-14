package com.twitter.kszdev.jenkins.pipeline

trait WithSharedLibrariesSources {

    String getSharedLibraryScriptPath(String scriptName) {
        return "vars/${scriptName}"
    }
}
