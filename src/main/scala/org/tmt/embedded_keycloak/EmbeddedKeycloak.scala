package org.tmt.embedded_keycloak

import org.tmt.embedded_keycloak.impl._
import org.tmt.embedded_keycloak.utils.Ports

import scala.concurrent.{ExecutionContext, Future}

class EmbeddedKeycloak(keycloakData: KeycloakData, settings: Settings = Settings.default) {

  private val wiring = new Wiring(keycloakData, settings)
  import settings._
  import wiring._

  private def preRun(): Unit = {
    installer.install()
    Ports.checkAvailability(port = port, throwOnError = true)
  }

  def startServer()(implicit ec: ExecutionContext): Future[StopHandle] = {
    preRun()

    val process = os
      .proc(
        "sh",
        fileIO.keycloakExecutablePath.toString,
        s"-Djboss.bind.address=$host",
        s"-Djboss.http.port=$port"
      )
      .spawn()

    val stopHandle = new StopHandle(process, port)

    healthCheck
      .checkHealth()
      .map(_ => dataFeeder.feed(keycloakData))
      .map(_ => stopHandle)
  }
}
