package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.builder.ZplBuilder

internal data object EndFormat : ZplCommand() {
    override val command: CharSequence = "^XZ"
}

/**
 * Ends the label format.
 */
fun ZplBuilder.endFormat() {
    command(EndFormat)
}