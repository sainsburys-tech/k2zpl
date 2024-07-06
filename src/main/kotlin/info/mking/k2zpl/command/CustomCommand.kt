package info.mking.k2zpl.command

internal data class CustomCommand(override val command: String) : ZplCommand {
    override val parameters: Map<String, Any?> = emptyMap()
}