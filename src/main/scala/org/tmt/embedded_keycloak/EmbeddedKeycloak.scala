package org.tmt.embedded_keycloak

import org.tmt.embedded_keycloak.impl.Bash._
import org.tmt.embedded_keycloak.impl.data.DataFeeder
import org.tmt.embedded_keycloak.impl.{FileIO, HealthCheck, Installer, StopHandle}
import org.tmt.embedded_keycloak.utils.Ports

import scala.concurrent.{ExecutionContext, Future}

class EmbeddedKeycloak(keycloakData: KeycloakData,
                       settings: Settings = Settings.default) {

  private val installer =
    new Installer(settings, keycloakData)

  private val healthCheck = new HealthCheck(settings)
  private val ports = new Ports()
  private val dataFeeder = new DataFeeder(settings)

  import settings._

  val fileIO = new FileIO(settings)

  private def preRun(): Unit = {
    installer.install()
    ports.checkAvailability(port = port, throwOnError = true)
  }

  def startServer()(implicit ec: ExecutionContext): Future[StopHandle] = {
    preRun()

    val process = background(
      s"sh ${fileIO.keycloakExecutablePath} " +
        s"-Djboss.bind.address=$host " +
        s"-Djboss.http.port=$port")

    val stopHandle = new StopHandle(process)

    healthCheck
      .checkHealth()
      .map(_ => dataFeeder.feed(keycloakData))
      .map(_ => stopHandle)
  }
}