# Embedded Keycloak

Embedded keycloak server for jvm integration testing. (developed in scala)

[![Build Status](https://travis-ci.com/bilal-fazlani/embedded-keycloak.svg?branch=master)](https://travis-ci.com/bilal-fazlani/embedded-keycloak)

[![Coverage Status](https://coveralls.io/repos/github/bilal-fazlani/embedded-keycloak/badge.svg?branch=master)](https://coveralls.io/github/bilal-fazlani/embedded-keycloak?branch=master)

# Usage

```scala
val keycloak = new EmbeddedKeycloak(
      KeycloakData.fromConfig, 
      Settings.default)

val stopHandle = await(keycloak.startServerInBackground())

//do some testing here

stopHandle.stop()
```

# Settings

the following settings options are available. 

The default setting looks like this -

```scala
Settings(port = 8081,
         host = "0.0.0.0",
         installationDirectory = "/tmp/keycloak-installation/",
         cleanPreviousData = true,
         alwaysDownload = false,
         version = "4.6.0")
```

# Keycloak Data

The test data can be provided in application.conf of test scope.

For example -

```hocon
embedded-keycloak{
    adminUser {
      username = admin
      password = admin
    }
    realms = [{
      name = example-realm
      realmRoles = [super-admin]
      clients = [
        {
          name = some-server
          clientType = bearer-only
          resourceRoles = [server-admin, server-user]
          authorizationEnabled = true
        },
        {
          name = some-client
        }
      ]
      users = [
        {
          username = user1
          password = abcd,
          realmRoles = [super-admin]
          firstName = john
        },
        {
          username = user2
          password = abcd,
          resourceRoles = [{
            clientName = some-server
            roleName = server-user
          }]
      }]
    }]
}
```