package kusuri12.bookfinder.global.exception

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class ForbiddenHandler: AccessDeniedHandler {

    private val log = KotlinLogging.logger {}

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        log.warn(accessDeniedException) {"Forbidden: ${accessDeniedException.message}"}
        response.sendError(HttpServletResponse.SC_FORBIDDEN)
    }
}