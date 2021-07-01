pipeline {
    agent any
    stages {
        stage('Checkout Repo') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/satta19/test-repo.git'
            }

        }
        
        stage('Create a Job') {
            steps {
                //Create a Jenkins Job
                jobDsl targets: 'test.groovy'
            }
        }
    }
}
