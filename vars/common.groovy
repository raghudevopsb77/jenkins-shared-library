def unitTests() {
  stage('Unit Tests') {
    echo 'OK'
  }
}

def integrationTests() {
  stage('Integration Tests') {
    echo 'OK'
  }
}

def codeQuality() {
  stage('Code Quality') {
    // achieve
    withCredentials([usernamePassword(credentialsId: 'SONARQUBE', passwordVariable: 'SONAR_PASS', usernameVariable: 'SONAR_USER')]) {
      sh 'sonar-scanner -Dsonar.host.url=http://sonarqube-int.rdevopsb73.online:9000 -Dsonar.login=${SONAR_USER} -Dsonar.password=${SONAR_PASS} -Dsonar.projectKey=${service_name} -Dsonar.qualitygate.wait=true'
    }
  }
}

def sast() {
  stage('SAST') {
    echo 'OK'
  }
}

def sca() {
  stage('SCA') {
    echo 'OK'
  }
}

def secretDetection() {
  stage('SECRET Detection') {
    //achive
    echo 'OK'
  }
}

def artifactProduce() {
  stage('Produce Artifact') {
    //achieve
    echo 'OK'
  }
}

def codeCheckout() {
  stage('CodeCheckout') {

    sh "find ."
    sh "find . | sed -e '1d' |xargs rm -rf"
    if(env.TAG_NAME ==~ ".*") {
      env.branch_name = "refs/tags/${env.TAG_NAME}"
    } else {
      env.branch_name = "${env.BRANCH_NAME}"
    }
    checkout scmGit(
        branches: [[name: "${branch_name}"]],
        userRemoteConfigs: [[url: "https://github.com/raghudevopsb77/${service_name}"]]
    )
  }
}
