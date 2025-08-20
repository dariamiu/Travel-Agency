module "one" {
    source = "git::ssh://git@aa/bb/repoccc.git//semantic-emr?ref=1.7.0"
}

something
something

module "two" {
    source = "git::ssh://git@dd/ee/repoff.git//preparation-glue?ref=1.7.0"
}

module "three" {
    source = "git::ssh://git@dd/ee/repoff.git//semantic-emr?ref=1.7.0"
}


module "itsm_alert_config" {
  count  = var.stage == "prod" ? 1 : 0
  source = "git::ssh://git@atc-github.azure.cloud.bmw/DAOTD/itsm-mapping-generator.git//infrastructure?ref=v1.0.0"
  stage  = var.stage
  itsm_default_alerts = [

    {
      module_name         = "one"
      job_names           = ["one"]
      implementation_type = module.itsm_alert_config[0].allowed_implementation_types.sem_emr
    },

    {
      module_name         = "two"
      job_names           = ["two"]
      implementation_type = module.itsm_alert_config[0].allowed_implementation_types.pre_glue
    },

    {
      module_name         = "three"
      job_names           = ["three"]
      implementation_type = module.itsm_alert_config[0].allowed_implementation_types.sem_emr
    }

  ]
}
