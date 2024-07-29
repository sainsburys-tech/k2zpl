@file:Suppress("UNUSED")

package info.mking.k2zpl.builder

import info.mking.k2zpl.command.EndFormat
import info.mking.k2zpl.command.FieldData
import info.mking.k2zpl.command.LabelLength
import info.mking.k2zpl.command.PrintQuantity
import info.mking.k2zpl.command.StartFormat
import info.mking.k2zpl.command.ZplCommand
import info.mking.k2zpl.command.options.ZplFont
import info.mking.k2zpl.command.options.ZplYesNo

/**
 * Adds field data.
 * @param data The data to be added to the field.
 */
fun ZplBuilder.fieldData(data: String) {
    command(FieldData(data))
}

/**
 * Sets the print quantity and related parameters.
 * @param quantity The number of labels to print.
 * @param labelsBetweenPauses The number of labels between pauses (optional).
 * @param replicates The number of times to replicate the label (optional).
 * @param noPause Whether to pause the printer (optional).
 * @param cutOnError Whether to cut on error (optional).
 */
fun ZplBuilder.printQuantity(
    quantity: Int,
    labelsBetweenPauses: Int? = null,
    replicates: Int? = null,
    noPause: Boolean = false,
    cutOnError: Boolean = false
) {
    command(PrintQuantity(quantity, labelsBetweenPauses, replicates, noPause, cutOnError))
}

/**
 * Sets the length of the label.
 * @param length The length of the label.
 */
fun ZplBuilder.labelLength(length: Int) {
    command(LabelLength(length))
}

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