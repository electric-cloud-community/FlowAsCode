def GitConfig = "Greg's RO GitHub"

project "/plugins/ECSCM/project",{
	aclEntry principalName: "GitHub", principalType: "serviceAccount", executePrivilege: "allow", modifyPrivilege: "allow"
}

// Workaround for https://cloudbees.atlassian.net/browse/CEV-24442?filter=-2
def NewServiceAccount="GitHub"
def Exists=false
getServiceAccounts().each { ServiceAccount ->
  if (ServiceAccount.serviceAccountName==NewServiceAccount) {
    Exists=true
  }
}
if (!Exists) serviceAccount NewServiceAccount

project 'FlowAsCode', {
	pipeline 'Check in handler', {
		stage 'Checkout and Evaluate', {
			colorCode = '#00adee'
			task 'Checkout', {
				actualParameter = [
					'clone': '0',
					'commit': '',
					'config': GitConfig,
					'depth': '',
					'dest': '../FlowAsCode',
					'GitBranch': '',
					'GitRepo': 'https://github.com/electric-cloud-community/FlowAsCode.git',
					'overwrite': '0',
					'tag': '',
				]
				subpluginKey = 'ECSCM-Git'
				subprocedure = 'CheckoutCode'
				taskType = 'PLUGIN'
			}
			task 'Apply DSL', {
				actualParameter = [
					'directory': '../FlowAsCode',
					'pool': 'local',
				]
				subpluginKey = 'EC-DslDeploy'
				subprocedure = 'installDslFromDirectory'
				taskType = 'PLUGIN'
			}
		}
	}
	schedule 'FlowAsCode Checkin', {
		pipelineName = 'Check in handler'
		timeZone = 'Etc/UTC'
		actualParameter 'ec_stagesToRun', '["Checkout and Evaluate"]'
		property 'ec_customEditorData', {
			TriggerFlag = '3'
			ec_maxRetries = '5'
			ec_quietTime = '0'
			ec_runDuplicates = '1'
			ec_webhookBranch = 'master'
			ec_webhookEventSource = 'github'
			ec_webhookEventType = 'push'
			ec_webhookRepositoryName = 'electric-cloud-community/FlowAsCode'
			formType = '$[/plugins/ECSCM-Git/project/scm_form/webhook]'
			scmConfig = GitConfig
		}
		ec_triggerPluginName = 'ECSCM-Git'
		ec_triggerType = 'webhook'
	}
}
