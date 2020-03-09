# FlowAsCode
Illustrating how to manage Flow configuration as code under source control
GitHub
Assumes subsequent edits only in DSL code

Dependencies
- git installed on agent

***Work in progress***

Create the initial DSL to be managed under source control, either directly authored in DSL or generted from a UI-authored model
When generating DSL consider using the options suppressDefaults and supressNulls to produce more human-readable code
- This can be done with the `ectool generateDsl --suppressDefaults true --supressNulls` command line
- Using the Self Service Catalog item, Export DSL
When applying the DSL, use the overwrite option to make sure that the DSL is used as the specification and not additive
- This can be done with the `ectool evalDsl --overwrite true` command line
- Using the Self Service Catalog item, Import DSL

Create the checkin handler
- Use a pipeline since this is the easiest way to trigger content in Flow from source control
- Check out code
-- Check out to ../<reponame> so that the code remain in a static location, not under a job workspace
-- Results to /myPipelineRuntime/CheckoutCode
- Apply the DSL
-- Use the plugin procedure EC-DslDeploy :: installDslFromDirectory
-- Overwrite true
- Verify the operation
- Create service account with a session key
- Create the GitHub trigger
- Set up a push webhook on the repo using the URL generated above



[] Git configuration
[] How got generate the DSL. Sample pipeline?
