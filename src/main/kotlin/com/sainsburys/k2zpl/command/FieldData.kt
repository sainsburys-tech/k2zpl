package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.builder.ZplBuilder

internal data class FieldData(val data: String) : ZplCommand {
    override val command: CharSequence = "^FD"
    override val parameters: Map<CharSequence, Any?> = addParameters("d" to data)

    override fun build(stringBuilder: StringBuilder): StringBuilder {
        return stringBuilder
            .append(command)
            .append(
                (parameters["d"] as? CharSequence).convertNewlines()
            )
    }

    private fun CharSequence?.convertNewlines(): String {
        return when {
            this == null -> ""
            else -> toString().replace("\n", "\\&")
        }
    }
}

/**
 * Adds field data.
 * @param data The data to be added to the field.
 */
fun ZplBuilder.fieldData(data: String) {
    command(FieldData(data))
}