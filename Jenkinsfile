pipeline {
    agent any

    environment {
        DOCKERHUB_USER = "naveed938"     
        IMAGE = "${DOCKERHUB_USER}/todo-cli-app:latest"
        REPO_URL = "https://github.com/SyedNaveedM/CICD_Lab" 
    }

    stages {

        stage('Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/main']],
                    userRemoteConfigs: [[ url: "${REPO_URL}" ]],
                    credentialsId: 'github-creds'
                ])
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${IMAGE} ."
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                credentialsId: 'docker-creds',
                usernameVariable: 'USER',
                passwordVariable: 'PASS'
            )]) {
                sh """
                    echo $PASS | docker login -u $USER --password-stdin
                    docker push ${IMAGE}
                """
            }
            }
        }

        stage('Deploy Container') {
            steps {
                sh """
                    docker pull ${IMAGE}
                    docker stop todo-app || true
                    docker rm todo-app || true
                    docker run -d -it --name todo-app ${IMAGE}
                """
            }
        }

    }
}
