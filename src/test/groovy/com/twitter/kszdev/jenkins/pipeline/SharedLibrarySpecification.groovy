package com.twitter.kszdev.jenkins.pipeline

import com.homeaway.devtools.jenkins.testing.JenkinsPipelineSpecification

class SharedLibrarySpecification extends JenkinsPipelineSpecification implements WithSharedLibrariesSources {

    Script loadSharedLibraryScript(String scriptName) {
        return loadPipelineScriptForTest(getSharedLibraryScriptPath(scriptName))
    }
}
