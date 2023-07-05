pipeline{
    agent any
    tools{maven 'M3'}
    stages{
        stage('Checkout'){
            steps{
                git branch:'master',url:'https://github.com/Prathyushak176/ChatterApp.git'
            }
        }
        stage('Build'){
            steps{
                bat 'mvn compile'
            }
        }
        stage('Test'){
            steps{
                bat 'mvn test'
            }
        }
        stage('Package'){
            steps{
                bat 'mvn package'
            }
        }
        stage('Deploy'){
            steps{
                echo 'deployed...'
            }
        }  
    }
}
