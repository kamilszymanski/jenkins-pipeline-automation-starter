package com.twitter.kszdev.jenkins.pipeline.callstack

import com.lesfurets.jenkins.unit.BasePipelineTest
import com.twitter.kszdev.jenkins.pipeline.WithJenkinsfilesSources
import org.junit.Before
import org.junit.Test

import static com.lesfurets.jenkins.unit.RegressionTestHelper.testNonRegression

class CallstackEqualityTest extends BasePipelineTest implements WithJenkinsfilesSources{

    private static final String EXPECTED_CALLSTACKS_PATH = 'src/test/resources/callstacks'

    @Before
    void setup() {
        super.setUp()
    }

    @Test
    void 'generated callstack should match expected one'() throws Exception {
        String jenkinsfileName = 'SampleScriptedJenkinsfile.groovy'
        String callstackFileBaseName = 'SimpleScriptedJenkinsfile-callstack'
        helper.registerAllowedMethod('ansiColor', [String, Closure], null)
        helper.registerAllowedMethod('timestamps', [Closure], null)

        runScript(getJenkinsfilePath(jenkinsfileName))

        testNonRegression(helper, "${EXPECTED_CALLSTACKS_PATH}/${callstackFileBaseName}")
    }
}
