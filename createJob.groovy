import hudson.model.*
 
// get current thread / Executor
def thr = Thread.currentThread()
// get current build
def build = thr?.executable



def hardcoded_param = "name"
def resolver = build.buildVariableResolver
def hardcoded_param_value = resolver.resolve(hardcoded_param)

def job_param = "JOB_NAME"
def resolver1 = build.buildVariableResolver
def job_name = resolver1.resolve(job_param)

// def jobName = "ALM/" + new Random().with {(1..9).collect {(('a'..'z')).join()[ nextInt((('a'..'z')).join().length())]}.join()}

println "param ${hardcoded_param} value : ${hardcoded_param_value}"


pipelineJob("$job_name") {
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
          agent {
               label 'java-docker-slave'
          }
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
 
          }
