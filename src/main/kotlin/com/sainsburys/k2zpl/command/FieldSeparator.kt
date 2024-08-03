package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.builder.ZplBuilder

internal data object FieldSeparator : ZplCommand {
    override val command: CharSequence = "^FS"
}

/**
 * Adds a field separator.
 */
fun ZplBuilder.fieldSeparator() {
    command(FieldSeparator)
}