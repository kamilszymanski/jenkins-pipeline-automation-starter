package com.twitter.kszdev.jenkins.pipeline

import com.homeaway.devtools.jenkins.testing.JenkinsPipelineSpecification

class ScriptedPipelineSpecification extends JenkinsPipelineSpecification implements WithJenkinsfilesSources {

    Script loadJenkinsfile(String jenkinsfileName) {
        return loadPipelineScriptForTest(getJenkinsfilePath(jenkinsfileName))
    }
}
