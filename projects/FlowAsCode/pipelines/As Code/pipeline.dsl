
pipeline 'As Code', {
  projectName = 'FlowAsCode'

  formalParameter 'ec_stagesToRun', {
    expansionDeferred = '1'
  }

stage "1st Stage"
stage "2nd Stage"

  acl {
    inheriting = '1'
  }
}
