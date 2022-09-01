package com.twitter.kszdev.jenkins.pipeline.jenkinsfile

import com.twitter.kszdev.jenkins.pipeline.ScriptedPipelineSpecification
import com.twitter.kszdev.jenkins.pipeline.WithFailedStep

/**
 * Tests are utilizing Jenkins Pipeline Support for Spock
 *
 * @see <a href="https://github.com/ExpediaGroup/jenkins-spock">Jenkins Pipeline Support for Spock</a>
 */
class SampleScriptedJenkinsfileSpec extends ScriptedPipelineSpecification implements WithFailedStep {

    Script jenkinsfile = loadJenkinsfile('SampleScriptedJenkinsfile.groovy')

    def 'should generate data'() {
        when:
            jenkinsfile.run()
        then:
            1 * getPipelineMock('sh')('date > generatedData.output')
    }

    def 'should clean workspace when data generation fails'() {
        when:
            jenkinsfile.run()
        then:
            0 * getPipelineMock('cleanWs.call')()
    }

    def 'should not clean workspace when data generation succeeds'() {
        given:
            getPipelineMock('sh')('date > generatedData.output') >> { failStep() }
        when:
            expectingUnhandledStepFailure {
                jenkinsfile.run()
            }
        then:
            1 * getPipelineMock('cleanWs.call')()
    }
}
