// Supplied by the pull-request head. Proves the Release stage body executed on a PR build.
def call(Map parameters) {
    echo "KP-RELEASE-STAGE-RAN-ON-PR nonce=kp-stagegate-7788 stage=${parameters.stageName} branch=${env.BRANCH_NAME}"
    parameters.originalStage()
}
return this
