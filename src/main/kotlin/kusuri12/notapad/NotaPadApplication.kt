package kusuri12.bookfinder

import kusuri12.bookfinder.global.jwt.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(JwtProperties::class)
class BookFinderApplication

fun main(args: Array<String>) {
    runApplication<BookFinderApplication>(*args)
}
