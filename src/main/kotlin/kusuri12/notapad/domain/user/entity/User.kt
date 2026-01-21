package kusuri12.bookfinder.domain.user.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class User (

    val id: Long,
    val name: String,
    val pass: String

) : UserDetails {
    override fun getUsername(): String = name
    override fun getPassword(): String = pass

    override fun getAuthorities(): Collection<GrantedAuthority> = emptyList()
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}