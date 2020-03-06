
project 'FlowAsCode', {

  acl {
    inheriting = '1'

    aclEntry 'user', principalName: 'project: FlowAsCode', {
      changePermissionsPrivilege = 'allow'
      executePrivilege = 'allow'
      modifyPrivilege = 'allow'
      readPrivilege = 'allow'
    }
  }
}
