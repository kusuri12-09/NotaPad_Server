package kusuri12.notapad.domain.user.exception

import kusuri12.notapad.global.exception.error.CustomException
import kusuri12.notapad.global.exception.error.ErrorCode

class InvalidCredential(): CustomException(ErrorCode.INVALID_CREDENTIAL) {
}
