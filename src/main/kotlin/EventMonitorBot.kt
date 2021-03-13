import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.message
import com.github.kotlintelegrambot.dispatcher.newChatMembers
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.extensions.filters.Filter
import com.github.kotlintelegrambot.network.fold
import io.github.cdimascio.dotenv.dotenv

fun main() {
    val dotenv = dotenv()

    bot {
        token = dotenv["TELEGRAM_ACCESS_TOKEN"]

        dispatch {
            message(Filter.Sticker) {
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = "You have received an awesome sticker \\o/"
                )
            }

            message(Filter.Reply or Filter.Forward) {
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = "someone is replying or forwarding messages ..."
                )
            }

            newChatMembers {
                newChatMembers.forEach {
                    bot.sendMessage(
                        chatId = ChatId.fromId(message.chat.id),
                        text = "Welcome! ${it.lastName} ${it.firstName} (${it.username}) [${it.id}] "
                    )
                }
            }
        }
    }.startPolling()
}
