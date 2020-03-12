/*

CloudBees Flow DSL: Manage Flow Configuration as code in source control - Checkin handler

This model (Pipeline, Schedule and Service Account) will repond to push Webhooks from GitHub and evaluate DSL stored in the repository.

Instructions
- Run this DSL, e.g., 'ectool evalDsl --dslFile CheckInHandler.groovy" or from the Flow DSL editor

*/

def Config = "Greg's RO GitHub"
def RepoBase = "https://github.com/"
def Repo = "electric-cloud-community/FlowAsCode"
def ServiceAccount="GitHub"

// Workaround for https://cloudbees.atlassian.net/browse/CEV-24442?filter=-2
def Exists=false
getServiceAccounts().each { SA ->
  if (SA.serviceAccountName==ServiceAccount) {
    Exists=true
  }
}
if (!Exists) serviceAccount ServiceAccount

project "/plugins/ECSCM/project",{
	aclEntry principalName: "GitHub", principalType: "serviceAccount", executePrivilege: "allow", modifyPrivilege: "allow"
}

project 'FlowAsCode', {
	pipeline 'Check in handler', {
		stage 'Checkout and Evaluate', {
			colorCode = '#00adee'
			task 'Checkout', {
				actualParameter = [
					'clone': '0',
					'commit': '',
					'config': Config,
					'depth': '',
					'dest': '../FlowAsCode',
					'GitBranch': '',
					'GitRepo': RepoBase + Repo,
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
					'overwrite':'1'
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
			ec_webhookRepositoryName = Repo
			formType = '$[/plugins/ECSCM-Git/project/scm_form/webhook]'
			scmConfig = Config
		}
		ec_triggerPluginName = 'ECSCM-Git'
		ec_triggerType = 'webhook'
	}
}
