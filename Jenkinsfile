pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'BUILDING PROJECT!!'
                withMaven(maven: 'mvn') {
                    sh 'mvn -B -DskipTests clean package'
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing in progress'
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deployment in process'
                sh "pwd && cd target"
                sh 'sudo systemctl stop grizzly-store.service'
                sh 'sudo rm /etc/init.d/grizzly-store || true'
                sh "sudo ln -s grizzlystore-order-0.0.1-SNAPSHOT.jar /etc/init.d/grizzly-store"
                sh "sudo systemctl start grizzly-store.service"
                /* sh 'mvn spring-boot:run'  java -jar grizzly-store-spring-1.0-SNAPSHOT.jar*/
            }
        }
    }
}
