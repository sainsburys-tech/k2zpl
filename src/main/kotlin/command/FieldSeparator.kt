package command

internal data object FieldSeparator : ZplCommand {
    override val command: String = "^FS"
    override val parameters: Map<String, Any?> = emptyMap()
}