package com.twitter.kszdev.jenkins.pipeline

trait WithFailedStep {

    void expectingUnhandledStepFailure(Closure closure) {
        try {
            closure.call()
            throw new IllegalStateException('Expected unhandled step failure was not observed')
        } catch(StepFailedException exception) {}
    }

    Closure failStep() {
        throw new StepFailedException()
    }

    static class StepFailedException extends RuntimeException {
    }
}
