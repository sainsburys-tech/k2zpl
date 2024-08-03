package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.builder.ZplBuilder

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