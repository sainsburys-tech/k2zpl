package info.mking.k2zpl.command

import info.mking.k2zpl.builder.ZplBuilder

internal data object EndFormat : ZplCommand {
    override val command: CharSequence = "^XZ"
}

/**
 * Ends the label format.
 */
fun ZplBuilder.endFormat() {
    command(EndFormat)
}