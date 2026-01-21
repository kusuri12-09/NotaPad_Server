package kusuri12.bookfinder.domain.user.exception

import kusuri12.bookfinder.global.exception.error.CustomException
import kusuri12.bookfinder.global.exception.error.ErrorCode

class InvalidCredential(): CustomException(ErrorCode.INVALID_CREDENTIAL) {
}
