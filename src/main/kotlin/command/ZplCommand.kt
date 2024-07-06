package command

interface ZplCommand {
    val command: String
    val parameters: Map<String, Any?>
}

object Command {
}