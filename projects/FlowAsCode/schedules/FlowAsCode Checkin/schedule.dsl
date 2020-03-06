schedule 'FlowAsCode Checkin', {
  pipelineName = 'Check in handler'
  timeZone = 'America/New_York'
  actualParameter 'ec_stagesToRun', '["Checkout and Evaluate"]'

  // Custom properties

  property 'ec_customEditorData', {

    // Custom properties
    TriggerFlag = '3'
    ec_maxRetries = '5'
    ec_quietTime = '0'
    ec_runDuplicates = '0'
    ec_webhookBranch = '*'
    ec_webhookEventSource = 'github'
    ec_webhookEventType = 'push'
    ec_webhookRepositoryName = 'electric-cloud-community/FlowAsCode'
    formType = '$[/plugins/ECSCM-Git/project/scm_form/webhook]'
    scmConfig = 'gmaxey'
  }
  ec_triggerPluginName = 'ECSCM-Git'
  ec_triggerType = 'webhook'
}
