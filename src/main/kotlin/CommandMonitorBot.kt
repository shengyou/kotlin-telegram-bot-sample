import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.network.fold
import io.github.cdimascio.dotenv.dotenv

fun main() {
    val dotenv = dotenv()

    bot {
        token = dotenv["TELEGRAM_ACCESS_TOKEN"]

        dispatch {
            command("greeting") {
                val words = listOf("Hello", "您好", "こんにちは", "Привет", "Ciao", "Guten Tag")
                val result = bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = words.random()
                )

                result.fold(
                    {
                        println("Result success")
                    },
                    {
                        println("Result failed")
                    }
                )
            }
        }
    }.startPolling()
}
