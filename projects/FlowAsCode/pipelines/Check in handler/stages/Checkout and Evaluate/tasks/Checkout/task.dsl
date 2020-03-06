
task 'Checkout', {
  actualParameter = [
    'clone': '0',
    'commit': '',
    'config': 'gmaxey',
    'depth': '',
    'dest': '../FlowAsCode',
    'GitBranch': '',
    'GitRepo': 'https://github.com/electric-cloud-community/FlowAsCode.git',
    'overwrite': '0',
    'resultPropertySheet': '/myJob/checkoutCode',
    'tag': '',
  ]
  projectName = 'FlowAsCode'
  subpluginKey = 'ECSCM-Git'
  subprocedure = 'CheckoutCode'
  taskType = 'PLUGIN'

  acl {
    inheriting = '1'
  }
}
