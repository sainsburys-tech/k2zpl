package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.builder.ZplBuilder

internal data class FieldData(val data: String, val replaceNewlines: Boolean = false) : ZplCommand {
    override val command: CharSequence = "^FD"
    override val parameters: LinkedHashMap<CharSequence, Any?> = linkedMapOf("d" to data)

    override fun build(stringBuilder: StringBuilder): StringBuilder {
        return with(stringBuilder) {
            append(command)
            append(
                (parameters["d"] as? CharSequence).convertNewlinesIf(replaceNewlines)
            )
        }
    }

    private fun CharSequence?.convertNewlinesIf(replaceNewlines: Boolean): String {
        return when {
            this == null -> ""
            replaceNewlines -> toString().replace("\n", "\\&")
            else -> toString()
        }
    }
}

/**
 * Adds field data.
 * @param data The data to be added to the field.
 * @param replaceNewlines set to true to replace \n with ZPL newline character (\&)
 */
fun ZplBuilder.fieldData(data: String, replaceNewlines: Boolean = false) {
    command(FieldData(data, replaceNewlines))
}