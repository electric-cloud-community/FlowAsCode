project "FlowAsCode",{
  pipeline "As Code",{
    stage "DEV",{
      colorCode = '#800000'
    }
    stage "QA",{
      colorCode = '#ff7f0e'
    }
    stage "Pre-prod",{
      colorCode = '#00adee'
    }
  }
}
