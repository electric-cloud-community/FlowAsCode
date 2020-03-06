
pipeline 'As Code', {
  projectName = 'FlowAsCode'

  formalParameter 'ec_stagesToRun', {
    expansionDeferred = '1'
  }

stage "First Stage"
  acl {
    inheriting = '1'
  }
}
