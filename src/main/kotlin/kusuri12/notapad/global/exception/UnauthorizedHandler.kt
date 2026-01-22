package kusuri12.notapad.global.exception

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class UnauthorizedHandler: AuthenticationEntryPoint {

    private val log = KotlinLogging.logger {}

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        log.warn(authException) {"Unauthorized: ${authException.message}"}
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
    }
}