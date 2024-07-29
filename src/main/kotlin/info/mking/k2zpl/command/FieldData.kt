package info.mking.k2zpl.command

import info.mking.k2zpl.builder.ZplBuilder
import info.mking.k2zpl.builder.command

internal data class FieldData(val data: String) : ZplCommand {
    override val command: CharSequence = "^FD"
    override val parameters: LinkedHashMap<CharSequence, Any?> = linkedMapOf("d" to data)
}

/**
 * Adds field data.
 * @param data The data to be added to the field.
 */
fun ZplBuilder.fieldData(data: String) {
    command(FieldData(data))
}