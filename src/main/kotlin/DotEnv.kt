import io.github.cdimascio.dotenv.dotenv

fun main() {
    val dotenv = dotenv()
    println(dotenv["TELEGRAM_ACCESS_TOKEN"])
}
