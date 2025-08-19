module one {
source = "git::ssh://git@aa/bb/repoccc.git//somelib?ref=1.7.0"
}

something
something

module two {
source = "git::ssh://git@dd/ee/repoff.git//somelib?ref=1.7.0"
}
