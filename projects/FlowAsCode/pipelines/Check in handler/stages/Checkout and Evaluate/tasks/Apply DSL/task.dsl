
task 'Apply DSL', {
  actualParameter = [
    'directory': '../FlowAsCode',
    'overwrite': '1',
    'pool': 'local',
  ]
  projectName = 'FlowAsCode'
  subpluginKey = 'EC-DslDeploy'
  subprocedure = 'installDslFromDirectory'
  taskType = 'PLUGIN'

  acl {
    inheriting = '1'
  }
}
