@Library('piper-lib-os') _

node {
    echo "KP-JENKINSFILE-FROM-MAIN"
    checkout scm
    setupCommonPipelineEnvironment script: this

    // Read back the two gates that piperPipeline uses to decide which stages run.
    // Both of these are supposed to be the pipeline owner's decision.
    echo "KP-BRANCH_NAME=" + env.BRANCH_NAME
    echo "KP-PRODUCTIVE-BRANCH=" + commonPipelineEnvironment.getStepConfiguration('', '').productiveBranch
    echo "KP-STAGE-CONFIG-Release=" + com.sap.piper.ConfigurationLoader.stageConfiguration(this, 'Release')
    echo "KP-STAGE-CONFIG-Promote=" + com.sap.piper.ConfigurationLoader.stageConfiguration(this, 'Promote')
    echo "KP-GENERAL-CONFIG=" + com.sap.piper.ConfigurationLoader.generalConfiguration(this)
}
