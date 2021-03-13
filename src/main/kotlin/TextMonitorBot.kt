import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.ParseMode
import io.github.cdimascio.dotenv.dotenv

fun main() {
    val dotenv = dotenv()

    bot {
        token = dotenv["TELEGRAM_ACCESS_TOKEN"]

        dispatch {
            text("ping") {
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = "Pong"
                )
            }

            text("markdown") {
                val markdownText = "_Cool message_: *Markdown* is `beatiful` :P"
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = markdownText,
                    parseMode = ParseMode.MARKDOWN
                )
            }
        }
    }.startPolling()
}
