pipeline {
  agent any
  stages {
    stage('Build and Test') {
      steps {
        withSonarQubeEnv('SonarQube') { // Match with your Jenkins SonarQube server environment
          sh './gradlew clean sonarqube -Dsonar.login=4f6c86e50462050c74c0b34a544434d66bb5d4fb'
        }
      }
    }

    stage("Quality Gate") {
      steps {
        timeout(time: 10, unit: 'MINUTES') {
          // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
          // true = set pipeline to UNSTABLE, false = don't
          waitForQualityGate abortPipeline: true
        }
      }
    }
  }
}