import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.webhook
import io.github.cdimascio.dotenv.dotenv
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    val dotenv = dotenv()
    val telegramToken = dotenv["TELEGRAM_ACCESS_TOKEN"]
    val telegramWebhookUrl = dotenv["TELEGRAM_WEBHOOK_URL"]

    val bot = bot {
        token = telegramToken

        webhook {
            url = telegramWebhookUrl
            //maxConnections = 50
            //allowedUpdates = listOf("message")
        }

        dispatch {
            text {
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = text
                )
            }
        }
    }

    bot.startWebhook()

    embeddedServer(Netty, port = 8080) {
        routing {
            post("/${telegramToken}") {
                val response = call.receiveText()

                bot.processUpdate(response)

                call.respond(HttpStatusCode.OK)
            }
        }
    }.start(wait = true)
}
