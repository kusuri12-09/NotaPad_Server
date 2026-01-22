package kusuri12.notapad.global.exception.error

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val code: String,
    val message: String
) {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER-404", "User Not Found"),
    USER_ALREADY_EXIST(HttpStatus.CONFLICT, "USER-409", "User Already Exist"),

    INVALID_CREDENTIAL(HttpStatus.BAD_REQUEST, "AUTH-400", "Invalid Credential"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH-401", "Unauthorized"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "AUTH-403", "Forbidden"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER-500", "Internal Server Error"),
    FAIL_TO_SEND_DISCORD(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER-500", "Failed to send discord webhook")
}