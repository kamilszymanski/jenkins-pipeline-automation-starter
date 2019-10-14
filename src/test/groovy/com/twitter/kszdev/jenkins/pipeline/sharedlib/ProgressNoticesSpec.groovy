package com.twitter.kszdev.jenkins.pipeline.sharedlib

import com.twitter.kszdev.jenkins.pipeline.SharedLibrarySpecification

class ProgressNoticesSpec extends SharedLibrarySpecification {

    def progressNotices = loadSharedLibraryScript('progressNotices.groovy')

    def 'should display progress notice'() {
        when:
            progressNotices.displayProgressNotice('Generating data')
        then:
            1 * getPipelineMock('sh')("echo -e '\\e[32mGenerating data\\e[0m'")
    }
}
