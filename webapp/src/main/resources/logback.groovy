import ch.qos.logback.classic.PatternLayout
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.LevelFilter

import static ch.qos.logback.core.spi.FilterReply.ACCEPT
import static ch.qos.logback.core.spi.FilterReply.DENY

def LOGS = "logs/stockx.log"
def ERRORS = "logs/errors/log"
appender("CONSOLE", ConsoleAppender) {
    layout(PatternLayout) {
        pattern = "%white(%d{ISO8601}) %highlight(%-5level) [%blue(%t)] %yellow(%C{1.}): %msg%n"
    }
}
appender("FILE", FileAppender) {
    file = "${LOGS}"
    append = true
    immediateFlush = true
    encoder(PatternLayoutEncoder) {
        pattern = "%d %p %C{1.} [%t] %m%n"
    }
}
appender("ERRORS_FILE", RollingFileAppender) {
    filter(LevelFilter) {
        level = ERROR
        onMatch = ACCEPT
        onMismatch = DENY
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${ERRORS}_%d{yyyy-MM-dd}.log"
    }
    encoder(PatternLayoutEncoder) {
        pattern = "%d %p %C{1.} [%t] %m%n"
    }
}
logger("org.springframework.web", TRACE, ["CONSOLE"])
logger("org.springframework", INFO, ["CONSOLE"], false)
logger("jm", INFO, ["CONSOLE", "FILE", "ERRORS_FILE"], false)