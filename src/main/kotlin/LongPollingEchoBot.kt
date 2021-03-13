import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId
import io.github.cdimascio.dotenv.dotenv

fun main() {
    val dotenv = dotenv()

    bot {
        token = dotenv["TELEGRAM_ACCESS_TOKEN"]

        dispatch {
            text {
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = text
                )
            }
        }
    }.startPolling()
}
