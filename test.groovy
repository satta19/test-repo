pipelineJob("UAT Environment") {
  logRotator {
      numToKeep(5)
  }

  parameters {
         stringParam('name', "", 'name of the person')
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
