package kusuri12.bookfinder.global.config

import jakarta.annotation.PostConstruct
import kusuri12.bookfinder.domain.user.service.AuthService
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class ExposedConfig(private val dataSource: DataSource) {

    @PostConstruct
    fun init() {
        Database.connect(dataSource)

        transaction {
            SchemaUtils.create(AuthService.UserTable)
        }
    }
}