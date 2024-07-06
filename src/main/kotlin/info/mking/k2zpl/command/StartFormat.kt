package info.mking.k2zpl.command

internal data object StartFormat : ZplCommand {
    override val command: String = "^XA"
    override val parameters: Map<String, Any?> = emptyMap()
}