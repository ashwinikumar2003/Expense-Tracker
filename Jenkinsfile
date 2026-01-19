
pipeline {
    agent any

    triggers {
        githubPush()
    }

    environment {
        COMPOSE_PROJECT_NAME = "expense-tracker-dev"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Images') {
            steps {
                sh '''
                  docker compose build
                '''
            }
        }

        stage('Restart Containers') {
            steps {
                sh '''
                  docker compose down
                  docker compose up -d
                '''
            }
        }
    }

    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}
