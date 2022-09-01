package com.twitter.kszdev.jenkins.pipeline.callstack

import com.lesfurets.jenkins.unit.BasePipelineTest
import com.twitter.kszdev.jenkins.pipeline.WithJenkinsfilesSources
import org.junit.After
import org.junit.Before
import org.junit.Test

import static com.lesfurets.jenkins.unit.RegressionTestHelper.PIPELINE_STACK_WRITE
import static com.lesfurets.jenkins.unit.RegressionTestHelper.testNonRegression

/**
 * Tests are utilizing Jenkins Pipeline Unit testing framework
 *
 * @see <a href="https://github.com/jenkinsci/JenkinsPipelineUnit">Jenkins Pipeline Unit</a>
 */
class ScriptedPipelineCallStackEqualityTest extends BasePipelineTest implements WithJenkinsfilesSources, WithExpectedCallStacksSources {

    private final String originalWriteCallStacksValue = System.getProperty(PIPELINE_STACK_WRITE)?: Boolean.FALSE.toString()

    /**
     * Setting `reGenerateCallStacks` to `true` re-generates call stacks before testing for regressions hence should be
     * disabled by default and in case of being used all generated call stacks' diffs should be manually reviewed
     */
    private boolean reGenerateCallStacks = false

    @Test
    void 'SampleScriptedJenkinsfile pipeline call stack should match expected call stack'() throws Exception {
        String jenkinsfile = 'SampleScriptedJenkinsfile'
        helper.registerAllowedMethod('ansiColor', [String, Closure], null)
        helper.registerAllowedMethod('timestamps', [Closure], null)

        runScript(jenkinsfilePath(jenkinsfile))

        testNonRegression(helper, callStackPath(jenkinsfile))
    }

    @Before
    void setup() {
        super.setUp()
        if (reGenerateCallStacks) {
            System.setProperty(PIPELINE_STACK_WRITE, Boolean.TRUE.toString())
        }
    }

    @After
    void cleanup() {
        System.setProperty(PIPELINE_STACK_WRITE, originalWriteCallStacksValue)
    }

    private String jenkinsfilePath(String jenkinsfile) {
        return getJenkinsfilePath("${jenkinsfile}.groovy")
    }

    private String callStackPath(String jenkinsfile) {
        return getExpectedCallStackFileNamePath("${jenkinsfile}-callstack")
    }
}
