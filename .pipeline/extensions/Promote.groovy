void call(Map params) {
    echo "KP-PROMOTE-STAGE-RAN-ON-PR nonce=kp-stagegate-7788 branch=${env.BRANCH_NAME}"
    params.originalStage()
}
return this
