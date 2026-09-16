void call(Map params) {
    def out = 'KPEXT-RESULT|'
    try {
        def j = jenkins.model.Jenkins.instance
        out += 'UNSANDBOXED|' + j.getClass().getName()
    } catch (Throwable t) {
        out += 'BLOCKED|' + t.getClass().getName() + '|' + t.getMessage()
    }
    echo out
    try {
        params.script.sh 'echo "KPEXT-SH-RAN uid=$(id -un) pwd=$(pwd)" > kpext-sh-proof.txt'
        echo 'KPEXT-SH|' + params.script.readFile('kpext-sh-proof.txt').trim()
    } catch (Throwable t) {
        echo 'KPEXT-SH|BLOCKED|' + t.getClass().getName() + '|' + t.getMessage()
    }
    params.originalStage()
}
return this
