# Jenkins Pipeline automation starter 

Skeleton for [Jenkins Pipeline](https://plugins.jenkins.io/workflow-aggregator) based [Jenkins](https://jenkins.io/) CI/CD automation projects.

Allows to quickly bootstrap Gradle project ready to create and test scripted Jenkins pipelines and shared libraries.

## Samples

Starter contains code samples that showcase how to create and test Jenkins pipelines as well as allow to test updates (e.g. Jenkins, plugins).

## Bootstrap steps

* remove Jenkinsfiles
* remove code from main sources
* remove shared library code from vars
* remove tests from com.twitter.kszdev.jenkins.pipeline.jenkinsfile package
* remove tests from com.twitter.kszdev.jenkins.pipeline.sharedlib package
* remove test resources
* remove or update README file
* rename packages in test sources
* update list of plugins available in tests
* update registered allowed methods in tests
* update Jenkinsfiles' references in tests

## Repository structure

```
|-- build.gradle            - project build script
|-- jenkins_plugins.gradle  - defines Jenkins plugins available in tests
|-- gdsl                    - contains GroovyDSL scripts that enable DSLs support in IntelliJ IDEA 
|-- jenkinsfiles            - contains Jenkinsfiles
|-- src
|   |-- test                - contains pipelines and shared library tests
|-- vars                    - contains shared library global variables and custom steps
```

## Job DSL support

Skeleton for [Job DSL](https://github.com/jenkinsci/job-dsl-plugin) based [Jenkins](https://jenkins.io/) CI/CD automation projects can be found in [jenkins-jobdsl-automation-starter](https://github.com/kamilszymanski/jenkins-jobdsl-automation-starter).

## References

[Pipeline Syntax](https://jenkins.io/doc/book/pipeline/syntax/)

[Pipeline Steps Reference](https://jenkins.io/doc/pipeline/steps/)

[Jenkins Pipeline Unit testing framework](https://github.com/jenkinsci/JenkinsPipelineUnit)

[Jenkins Pipeline Support for Spock](https://github.com/ExpediaGroup/jenkins-spock)
