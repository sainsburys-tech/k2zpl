package info.mking.k2zpl.command

import info.mking.k2zpl.builder.ZplBuilder

internal data object StartFormat : ZplCommand {
    override val command: CharSequence = "^XA"
}

/**
 * Starts the label format.
 */
fun ZplBuilder.startFormat() {
    command(StartFormat)
}