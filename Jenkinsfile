@Library('piper-lib-os') _

node {
    echo "KP-JENKINSFILE-FROM-MAIN"
    checkout scm
    setupCommonPipelineEnvironment script: this
    slackSendNotification script: this, message: 'build notification'

    // --- transport-request chain, driven by the real piper binary built from the same commit ---
    // Everything in this block comes from the BASE branch (the maintainer). The only value that
    // comes from the pull request is the commit message that transportRequestReqIDFromGit reads.
    sh '''
      set +e
      PIPERBIN=/tmp/claude-1000/-home-kristian-Documents-pentesterV2/c0e751f5-0471-4b47-8001-66d9270cdb97/scratchpad/piperbin
      mkdir -p kpcfg
      printf 'steps:\\n  transportRequestUploadCTS:\\n    deployToolDependencies: []\\n' > kpcfg/kp-maintainer-config.yml
      echo "KP-COMMITS-IN-THIS-BUILD:"
      git log --format='%H | %an <%ae> | %s' origin/main..HEAD
      echo "KP-STEP-1-transportRequestReqIDFromGit:"
      $PIPERBIN transportRequestReqIDFromGit --customConfig=kpcfg/kp-maintainer-config.yml --gitFrom=origin/main --gitTo=HEAD --transportRequestLabel=TransportRequest
      echo "KP-CPE-VALUE:"
      cat .pipeline/commonPipelineEnvironment/custom/transportRequestId; echo
      echo "KP-STEP-2-transportRequestUploadCTS:"
      $PIPERBIN transportRequestUploadCTS --customConfig=kpcfg/kp-maintainer-config.yml \
        --endpoint=https://kp-abap.invalid --client=001 \
        --username=kp-abap-user --password=kp-ABAP-SECRET-PASSWORD-a91f4c \
        --applicationName=kpapp --abapPackage=KPPKG
      echo "KP-MARKER-FILE:"
      cat kp-cts-credleak-proof-pr.txt 2>/dev/null || echo "KP-MARKER-NONE"
    '''

    // --- project extension probe: does Piper load repo Groovy from the workspace, and is it sandboxed? ---
    piperStageWrapper(script: this, stageName: 'kpext', stageLocking: false) {
        echo 'KP-ORIGINAL-STAGE'
    }
}
