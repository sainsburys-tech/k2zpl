package info.mking.k2zpl.command

internal data class FieldData(val data: String) : ZplCommand {
    override val command: String = "^FD"
    override val parameters: Map<String, Any?> = mapOf("d" to data)
}