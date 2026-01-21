package kusuri12.bookfinder.domain.user.presentation.dto.request

data class LoginRequest(
    val username: String,
    val password: String
)