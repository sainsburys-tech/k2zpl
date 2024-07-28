package info.mking.k2zpl.command

import info.mking.k2zpl.builder.ZplBuilder
import info.mking.k2zpl.builder.command

internal data class PrintWidth(val width: Int) : ZplCommand {
    init {
        require(width in 2 .. 32000) { "Width must be greater than 2. Value is also capped at width of the actual label." }
    }

    override val command: CharSequence = "^PW"
    override val parameters: LinkedHashMap<CharSequence, Any?> = linkedMapOf("w" to width)
}


/**
 * Sets the print width of the label.
 * @param width The width of the label.
 */
fun ZplBuilder.printWidth(width: Int) {
    command(PrintWidth(width = width))
}