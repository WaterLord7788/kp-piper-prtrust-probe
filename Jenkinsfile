@Library('piper-lib-os') _

node {
    echo "KP-JENKINSFILE-FROM-MAIN"
    checkout scm
    setupCommonPipelineEnvironment script: this
    slackSendNotification script: this, message: 'build notification'
}
