package info.mking.k2zpl.command

internal data class FieldData(val data: String) : ZplCommand {
    override val command: CharSequence = "^FD"
    override val parameters: Map<CharSequence, Any?> = mapOf("d" to data)
}