package com.twitter.kszdev.jenkins.pipeline.callstack

import com.lesfurets.jenkins.unit.declarative.DeclarativePipelineTest
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
class DeclarativePipelineCallStackEqualityTest extends DeclarativePipelineTest implements WithJenkinsfilesSources, WithExpectedCallStacksSources {

    private final String originalWriteCallStacksValue = System.getProperty(PIPELINE_STACK_WRITE)?: Boolean.FALSE.toString()

    /**
     * Setting `reGenerateCallStacks` to `true` re-generates call stacks before testing for regressions hence should be
     * disabled by default and in case of being used all generated call stacks' diffs should be manually reviewed
     */
    private boolean reGenerateCallStacks = false

    @Test
    void 'SampleDeclarativeJenkinsfile pipeline call stack should match expected call stack'() throws Exception {
        String jenkinsfile = 'SampleDeclarativeJenkinsfile'
        helper.registerAllowedMethod('ansiColor', [String, Closure], null)
        helper.registerAllowedMethod('timestamps', [Closure], null)

        compareCallStacks(jenkinsfilePath(jenkinsfile), callstackPath(jenkinsfile))
    }

    @Test
    void 'SampleDeclarativeJenkinsfileWithMultiOSSupport pipeline call stack on Unix system should match expected call stack'() throws Exception {
        String jenkinsfile = 'SampleDeclarativeJenkinsfileWithMultiOSSupport'
        helper.registerAllowedMethod("isUnix", [], { return true })
        helper.registerAllowedMethod('ansiColor', [String, Closure], null)
        helper.registerAllowedMethod('timestamps', [Closure], null)

        compareCallStacks(jenkinsfilePath(jenkinsfile), callstackPath(jenkinsfile, 'unix'))
    }

    @Test
    void 'SampleDeclarativeJenkinsfileWithMultiOSSupport pipeline call stack on Windows system should match expected call stack'() throws Exception {
        String jenkinsfile = 'SampleDeclarativeJenkinsfileWithMultiOSSupport'
        helper.registerAllowedMethod("isUnix", [], { return false })
        helper.registerAllowedMethod('timestamps', [Closure], null)

        compareCallStacks(jenkinsfilePath(jenkinsfile), callstackPath(jenkinsfile, 'windows'))
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

    private void compareCallStacks(String jenkinsfilePath, String expectedCallStackPath) {
        runScript(jenkinsfilePath)
        testNonRegression(helper, expectedCallStackPath)
    }

    private String jenkinsfilePath(String jenkinsfile) {
        return getJenkinsfilePath("${jenkinsfile}.groovy")
    }

    private String callstackPath(String jenkinsfile) {
        return getExpectedCallStackFileNamePath("${jenkinsfile}-callstack")
    }

    private String callstackPath(String jenkinsfile, String platform) {
        return getExpectedCallStackFileNamePath("${jenkinsfile}-${platform}-callstack")
    }
}
