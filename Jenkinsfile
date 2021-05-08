// //for mac and windows, use the below version
// //normally at workplace, we will use linux as it is more lightweight.
// pipeline {
//     // master executor should be set to 0
//     agent any
//     stages {
//         stage('Build Jar') {
//             steps {
//                 //sh
//                 sh "mvn clean package -DskipTests"
//             }
//         }
//         stage('Build Image') {
//             steps {
//                 //sh
//                 sh "docker build -t='ismailqa/selenium-docker' ."
//             }
//         }
//         stage('Push Image') {
//             steps {
// 			    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
//                     //sh
// 			        sh "docker login --username=${user} --password=${pass}"
// 			        sh "docker push ismailqa/selenium-docker:latest"
// 			    }
//             }
//         }
//     }
// }
//This below one is for linux
pipeline {
    agent none
    stages {
        stage('Build Jar') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build Image') {
            steps {
                script {
                	app = docker.build("ismailqa/selenium-docker")
                }
            }
        }
        stage('Push Image') {
            steps {
                script {
			        docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
			        	app.push("${BUILD_NUMBER}")
			            app.push("latest")
			        }
                }
            }
        }
    }
}