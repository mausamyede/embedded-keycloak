package org.tmt.embedded_keycloak

import os.{Inherit, Pipe, ProcessOutput}

case class Settings(
    port: Int = 8081,
    host: String = "0.0.0.0",
    keycloakDirectory: String = System.getProperty("user.home") + "/embedded-keycloak/",
    cleanPreviousData: Boolean = true,
    alwaysDownload: Boolean = false,
    version: String = "11.0.0",
    printProcessLogs: Boolean = true
) {
  val stdOutLogger: ProcessOutput = if (printProcessLogs) Inherit else Pipe
}

object Settings {
  val default: Settings = Settings()
}
