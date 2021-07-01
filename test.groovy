import hudson.model.*
 
// get current thread / Executor
def thr = Thread.currentThread()
// get current build
def build = thr?.executable



def hardcoded_param = "name"
def resolver = build.buildVariableResolver
def hardcoded_param_value = resolver.resolve(hardcoded_param)

pipelineJob("UAT Environment") {
  logRotator {
      numToKeep(5)
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
                          echo "Hello ${name}"
                      }
                  }
              }
          }
    '''.stripIndent())

        sandbox()
          }
                  
              }
 
          }
