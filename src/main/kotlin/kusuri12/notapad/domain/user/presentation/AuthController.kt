package kusuri12.notapad.domain.user.presentation

import kusuri12.notapad.domain.user.presentation.dto.request.LoginRequest
import kusuri12.notapad.domain.user.presentation.dto.request.SignUpRequest
import kusuri12.notapad.domain.user.presentation.dto.response.LoginResponse
import kusuri12.notapad.domain.user.presentation.dto.response.SignUpResponse
import kusuri12.notapad.domain.user.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.ok().body(LoginResponse(authService.login(request.username, request.password)))
    }

    @PostMapping("/sign_up")
    fun signUp(@RequestBody request: SignUpRequest): ResponseEntity<SignUpResponse> {
        return ResponseEntity.ok().body(SignUpResponse(authService.signUp(request.username, request.password)))
    }
}