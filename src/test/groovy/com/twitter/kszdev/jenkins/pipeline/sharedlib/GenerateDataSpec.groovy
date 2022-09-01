package com.twitter.kszdev.jenkins.pipeline.sharedlib

import com.twitter.kszdev.jenkins.pipeline.SharedLibrarySpecification

class GenerateDataSpec extends SharedLibrarySpecification {

    def 'should generate data'() {
        given:
            def generateData = loadSharedLibraryScript('generateData.groovy')
        when:
            generateData('outputFile')
        then:
            1 * getPipelineMock('sh')('date > outputFile')
    }
}
