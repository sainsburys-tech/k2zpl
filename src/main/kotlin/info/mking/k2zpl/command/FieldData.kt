package info.mking.k2zpl.command

internal data class FieldData(val data: String) : ZplCommand {
    override val command: CharSequence = "^FD"
    override val parameters: LinkedHashMap<CharSequence, Any?> = linkedMapOf("d" to data)
}