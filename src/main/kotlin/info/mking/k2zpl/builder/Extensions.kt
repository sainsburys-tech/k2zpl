@file:Suppress("UNUSED")

package info.mking.k2zpl.builder

import info.mking.k2zpl.command.EndFormat
import info.mking.k2zpl.command.StartFormat
import info.mking.k2zpl.command.ZplCommand
import info.mking.k2zpl.command.options.ZplFont
import info.mking.k2zpl.command.options.ZplYesNo

/**
 * Starts the label format.
 */
fun ZplBuilder.startFormat() {
    command(StartFormat)
}

/**
 * Ends the label format.
 */
fun ZplBuilder.endFormat() {
    command(EndFormat)
}

/**
 * Shortcut for addCommand
 */
fun ZplBuilder.command(command: ZplCommand) {
    addCommand(command)
}

/**
 * Shortcut for addCommand for string
 */
fun ZplBuilder.command(command: String) {
    addCommand(command)
}

/**
 * Shortcut for addField
 */
fun ZplBuilder.field(x: Int = 0, y: Int = 0, data: String) {
    addField(x, y, data)
}

/**
 * Shortcut for addField with font attributes
 */
fun ZplBuilder.field(x: Int, y: Int, font: ZplFont, fontHeight: Int, fontWidth: Int, data: String) {
    addField(x, y, font, fontHeight, fontWidth, data)
}

internal fun Boolean.toZplYesNo(): ZplYesNo = when(this) {
    true -> ZplYesNo.YES
    else -> ZplYesNo.NO
}