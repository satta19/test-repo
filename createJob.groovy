import hudson.model.*
 
// get current thread / Executor
def thr = Thread.currentThread()
// get current build
def build = thr?.executable

def jobName = "ALM/" + new Random().with {(1..9).collect {(('a'..'z')).join()[ nextInt((('a'..'z')).join().length())]}.join()}

def hardcoded_param = "name"
def resolver = build.buildVariableResolver
def hardcoded_param_value = resolver.resolve(hardcoded_param)
 
 
println "param ${hardcoded_param} value : ${hardcoded_param_value}"


pipelineJob("$jobName") {
  logRotator {
      numToKeep(5)
      artifactNumToKeep(1)
  }

  parameters {
         stringParam('name', "${hardcoded_param_value}", 'name of the person')
    }

    definition {
          cps {
          script('''
      pipeline {
          agent any
              stages {
                  stage('Stage 1') {
                      steps {
                          echo 'logic'
                          echo "${name}"
                      }
                  }
                  stage('Stage 2') {
                      steps {
                          echo 'logic'
                      }
                  }
              }
          }
    '''.stripIndent())

        sandbox()
          }
                  
              }
 
 publishers {
        downstreamParameterized {
            trigger('$jobName') {
                condition('STABLE')
                parameters {
                    currentBuild()
                }
            }
        }
    }
 
          }
