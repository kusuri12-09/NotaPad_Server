package kusuri12.notapad.global.exception.error

import org.springframework.http.HttpStatus

data class ErrorResponse(
    val status: HttpStatus,
    val message: String
)