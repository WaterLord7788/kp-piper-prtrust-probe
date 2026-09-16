@Library('piper-lib-os') _

node {
    echo "KP-JENKINSFILE-FROM-PR-HEAD-SHOULD-NOT-APPEAR"
    checkout scm
    setupCommonPipelineEnvironment script: this
    slackSendNotification script: this, message: 'build notification'
}
