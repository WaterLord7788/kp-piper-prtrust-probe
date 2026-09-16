@Library('piper-lib-os') _

node {
    echo "KP-JENKINSFILE-FROM-MAIN"
    checkout scm
    setupCommonPipelineEnvironment script: this

    echo "KP-WORKSPACE-BEFORE-PIPER-STEP:"
    sh 'ls -la . | head -30; echo "---"; if [ -x ./piper ]; then echo "KP-EXEC-PIPER-PRESENT"; else echo "KP-EXEC-PIPER-ABSENT"; fi'

    // An ordinary, documented Piper step invoked the ordinary way. Everything here
    // comes from the base branch. piperExecuteBin -> PiperGoUtils.unstashPiperBin().
    transportRequestReqIDFromGit(
        script: this,
        gitFrom: 'origin/main',
        gitTo: 'HEAD',
        transportRequestLabel: 'TransportRequest'
    )

    echo "KP-PROOF-FILE:"
    sh 'cat ./kp-piperbin-substitution-proof.txt 2>/dev/null || echo KP-PROOF-NONE'
}
