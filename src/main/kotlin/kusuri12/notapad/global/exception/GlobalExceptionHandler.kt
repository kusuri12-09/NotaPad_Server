package kusuri12.notapad.global.exception

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import kusuri12.notapad.global.exception.error.ErrorCode
import kusuri12.notapad.global.exception.error.ErrorResponse
import kusuri12.notapad.global.feignclient.discord.DiscordWebhookClient
import kusuri12.notapad.global.feignclient.discord.DiscordWebhookRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler(private val discordWebhookClient: DiscordWebhookClient) {
    private val log = KotlinLogging.logger {}

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        log.error(e) { "Unexpected Exception: ${e.message}"}
        sendDiscordAlert(e, request)

        val response = ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR,
            message = ErrorCode.INTERNAL_SERVER_ERROR.message)

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
    }

    private fun sendDiscordAlert(e: Exception, request: HttpServletRequest) {
        try {
            val content = buildMessage(e, request)
            discordWebhookClient.send(DiscordWebhookRequest(content))
        } catch (ex: Exception) {
            log.error(ex) {"Failed to send discord webhook: ${ex.message}"}
        }
    }

    private fun buildMessage(e: java.lang.Exception, request: HttpServletRequest): String {
        val errorMessage = "Internal Server Error" + "\n" +
                "- Method: ``${request.method}``" + "\n" +
                "- URI: ``${request.requestURI}`` " + "\n" +
                "- ${e.message}"

        return if (errorMessage.length > 1990) {
            errorMessage.substring(0, 1990) + "..."
        } else {
            errorMessage
        }
    }
}