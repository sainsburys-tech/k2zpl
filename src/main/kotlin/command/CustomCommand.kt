package command

internal data class CustomCommand(override val command: String) : ZplCommand {
    override val parameters: Map<String, Any?> = emptyMap()
}