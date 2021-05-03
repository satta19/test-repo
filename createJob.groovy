def jobName = "ALM/" + new Random().with {(1..9).collect {(('a'..'z')).join()[ nextInt((('a'..'z')).join().length())]}.join()}


pipelineJob("$jobName") {
  logRotator {
      numToKeep(5)
      artifactNumToKeep(1)
  }

  parameters {
         stringParam('name', 'john', 'name of the person')
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
          }
