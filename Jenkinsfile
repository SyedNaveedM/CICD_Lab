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
                bat 'wsl mvn clean package'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'wsl mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat "wsl docker build -t ${IMAGE} ."
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {
                    bat """
                        echo %PASS% | wsl docker login -u %USER% --password-stdin
                        wsl docker push ${IMAGE}
                    """
                }
            }
        }

        stage('Deploy Container') {
            steps {
                bat """
                    wsl docker pull ${IMAGE}
                    wsl docker stop todo-app || true
                    wsl docker rm todo-app || true
                    wsl docker run -d -it --name todo-app ${IMAGE}
                """
            }
        }

    }
}
