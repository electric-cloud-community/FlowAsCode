
pipeline 'Check in handler', {
  projectName = 'FlowAsCode'

  formalParameter 'ec_stagesToRun', {
    expansionDeferred = '1'
  }

  acl {
    inheriting = '1'
  }
}
