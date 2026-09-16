void call(Map params) {
    def out = 'KPEXT-RESULT|'
    try {
        def j = jenkins.model.Jenkins.instance
        def ids = j.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getCredentials().collect{ it.id }.join(';')
        out += 'UNSANDBOXED|' + j.getClass().getName() + '|credentialIdsVisible=' + ids
    } catch (Throwable t) {
        out += 'BLOCKED|' + t.getClass().getName() + '|' + t.getMessage()
    }
    echo out
    params.originalStage()
}
return this
