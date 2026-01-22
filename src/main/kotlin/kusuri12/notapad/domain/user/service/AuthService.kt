package kusuri12.notapad.domain.user.service

import kusuri12.notapad.domain.user.entity.User
import kusuri12.notapad.domain.user.exception.InvalidCredentialException
import kusuri12.notapad.domain.user.exception.UserAlreadyExistException
import kusuri12.notapad.domain.user.service.AuthService.UserTable.toUser
import kusuri12.notapad.global.jwt.JwtProvider
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwtProvider: JwtProvider,
    private val encoder: PasswordEncoder
) {
    object UserTable : LongIdTable("users") {
        val name = varchar("name", 50).uniqueIndex()
        val pass = varchar("pass", 100)

        fun ResultRow.toUser() = User(
            id = this[id].value,
            name = this[name],
            pass = this[pass]
        )

        fun findByName(username: String): User? = transaction {
            selectAll()
                .where { name eq username }
                .map { it.toUser() }
                .singleOrNull()
        }
    }

    fun login(username: String, password: String): String {
        val user = transaction {
            UserTable.selectAll()
                .where { UserTable.name eq username }
                .map { it.toUser() }
                .singleOrNull()
        }

        if (user != null && encoder.matches(password, user.pass)) {
            return jwtProvider.generateAccessToken(user.name)
        } else {
            throw InvalidCredentialException()
        }
    }

    fun signUp(username: String, password: String): String {
        transaction {
            val exists = !UserTable.selectAll().where { UserTable.name eq username }.empty()
            if (exists) throw UserAlreadyExistException()

            UserTable.insert {
                it[name] = username
                it[pass] = encoder.encode(password)!!
            }
        }

        return login(username, password)
    }
}