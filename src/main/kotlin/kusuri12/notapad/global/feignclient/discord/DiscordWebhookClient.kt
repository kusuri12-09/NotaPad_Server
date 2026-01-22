package kusuri12.notapad.global.feignclient.discord

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "discordWebhookClient", url = "\${discord.webhook.url}")
interface DiscordWebhookClient {
    @PostMapping
    fun send(@RequestBody request: DiscordWebhookRequest)
}