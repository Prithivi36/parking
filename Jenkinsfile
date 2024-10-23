pipeline {
    agent {
        node {
            label 'backendazure'
        }
    }
    tools {
        maven 'maven'
    }
    stages {
        stage("Checkout") {
            steps {
                git url: 'https://github.com/Prithivi36/paraking', branch: 'main'
            }
        }
        stage("build") {
            steps {
                sh 'mvn clean install'
            }
        }
        stage("deploy") {
            steps {
                sh '''
                cd target
                ls
                java -jar paraking-0.0.1-SNAPSHOT.jar
                '''
            }
        }
    }
}