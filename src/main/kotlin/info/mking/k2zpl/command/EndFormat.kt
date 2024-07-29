package info.mking.k2zpl.command

import info.mking.k2zpl.builder.ZplBuilder
import info.mking.k2zpl.builder.command

internal data object EndFormat : ZplCommand {
    override val command: CharSequence = "^XZ"
}

/**
 * Ends the label format.
 */
fun ZplBuilder.endFormat() {
    command(EndFormat)
}