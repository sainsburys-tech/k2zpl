package info.mking.k2zpl.command

interface ZplCommand {
    val command: String
    val parameters: Map<String, Any?>
}