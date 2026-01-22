package kusuri12.notapad

import kusuri12.notapad.global.jwt.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(JwtProperties::class)
class NotaPadApplication

fun main(args: Array<String>) {
    runApplication<NotaPadApplication>(*args)
}
