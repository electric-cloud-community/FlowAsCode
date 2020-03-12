# FlowAsCode

This project illustrates how CloudBee Flow configurations can be managed under source control. This repository contains two items:
- CheckInHandler.groovy which creates the necessary content in Flow to respond to push webhooks from this repository
- projects - The DSL code for a simple pipeline. This code was generated using the DSL Export Self-Service Catalog item

When a push to this repository is detected in GitHub, GitHub will send Flow a webhook. This webhook will be processed by the plugin ECSCM. If it finds a match, it will run the schedule (trigger) associated with the Check in handle pipeline. This pipeine will retrieve the new source code to workspace/.. and apply the DSL there.

Dependencies
- git installed on the local agent
- EC-DslDeploy installed and promoted
- GitHub account

Instructions
- Clone this repository
- Create an SCM git configuration
- Update checkInHandler.groovy with that configuration name and the repository name
- Run the DSL in the file CheckInHandler.groovy
- Create a session ID
- Edit the Check in Handler pipeline trigger and point to the session ID and copy the URL
- Create a webhook to your repository using the URL from above
- Push a commit to the GitHub repository

Debugging
- Examine the GitHub webhook request, if 200, then
- Examine the Jobs in the plugin directory ECSCM, if the last one succeeded, then
- Examine the latest pipeline run in FlowAsCode :: Check in handler

Considerations
- When generating DSL consider using the options suppressDefaults and supressNulls to produce more human-readable code
 - This can be done with the `ectool generateDsl --suppressDefaults true --supressNulls` command line
 - Using the Self Service Catalog item, Export DSL
