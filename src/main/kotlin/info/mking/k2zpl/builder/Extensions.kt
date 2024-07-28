@file:Suppress("UNUSED")

package info.mking.k2zpl.builder

import info.mking.k2zpl.command.EndFormat
import info.mking.k2zpl.command.FieldBlock
import info.mking.k2zpl.command.FieldData
import info.mking.k2zpl.command.FieldOrientation
import info.mking.k2zpl.command.FieldOrigin
import info.mking.k2zpl.command.FieldSeparator
import info.mking.k2zpl.command.Font
import info.mking.k2zpl.command.FontAt
import info.mking.k2zpl.command.GraphicField
import info.mking.k2zpl.command.LabelHome
import info.mking.k2zpl.command.LabelLength
import info.mking.k2zpl.command.LabelShift
import info.mking.k2zpl.command.MediaDarkness
import info.mking.k2zpl.command.PrintQuantity
import info.mking.k2zpl.command.PrintRate
import info.mking.k2zpl.command.PrintWidth
import info.mking.k2zpl.command.StartFormat
import info.mking.k2zpl.command.ZplCommand
import info.mking.k2zpl.command.options.ZplFieldOrientation
import info.mking.k2zpl.command.options.ZplFont
import info.mking.k2zpl.command.options.ZplPrintSpeed
import info.mking.k2zpl.command.options.ZplTextAlignment
import info.mking.k2zpl.command.options.ZplYesNo

/**
 * Sets the print width of the label.
 * @param width The width of the label.
 */
fun ZplBuilder.printWidth(width: Int) {
    command(PrintWidth(width = width))
}

/**
 * Sets the label home position.
 * @param x The x-coordinate of the label home.
 * @param y The y-coordinate of the label home.
 */
fun ZplBuilder.labelHome(x: Int, y: Int) {
    command(LabelHome(x = x, y = y))
}

/**
 * Sets the label shift.
 * @param shift The shift amount.
 */
fun ZplBuilder.labelShift(shift: Int) {
    command(LabelShift(shift = shift))
}

/**
 * Sets the font for text fields.
 * @param font The font to use.
 * @param height The height of the font.
 * @param width The width of the font.
 */
fun ZplBuilder.font(font: ZplFont, height: Int, width: Int) {
    command(Font(
        font = font,
        height = height,
        width = width
    ))
}

/**
 * Sets the font with additional path parameter.
 * @param orientation The orientation of the font.
 * @param height The height of the font.
 * @param width The width of the font.
 * @param path The path to the font file.
 */
fun ZplBuilder.fontAt(orientation: ZplFieldOrientation, height: Int, width: Int, path: String) {
    command(FontAt(
        orientation = orientation,
        height = height,
        width = width,
        path = path
    ))
}

/**
 * Formats a text block.
 * @param width The width of the text block.
 * @param lines The number of lines in the text block.
 * @param lineSpacing The space between lines.
 * @param alignment The text alignment within the block.
 * @param hangingIndent The hanging indent for the block.
 */
fun ZplBuilder.fieldBlock(width: Int, lines: Int, lineSpacing: Int, alignment: ZplTextAlignment, hangingIndent: Int) {
    command(FieldBlock(
        width = width,
        lines = lines,
        lineSpacing = lineSpacing,
        alignment = alignment,
        hangingIndent = hangingIndent
    ))
}

/**
 * Sets the field orientation for text fields.
 * @param orientation The orientation of the field.
 */
fun ZplBuilder.defaultFieldOrientation(orientation: ZplFieldOrientation) {
    command(command = FieldOrientation(orientation = orientation))
}

/**
 * Sets the print rate.
 * @param speed The print speed.
 * @param darkness The print darkness.
 */
fun ZplBuilder.defaultPrintRate(speed: ZplPrintSpeed, darkness: Int) {
    command(PrintRate(speed = speed, darkness = darkness))
}

/**
 * Sets the media darkness.
 * @param darkness The darkness level.
 */
fun ZplBuilder.defaultMediaDarkness(darkness: Int) {
    command(MediaDarkness(darkness = darkness))
}

/**
 * Sets the origin for a field.
 * @param x The x-coordinate of the field origin.
 * @param y The y-coordinate of the field origin.
 * @param alignment The alignment of the field (optional).
 */
fun ZplBuilder.fieldOrigin(x: Int, y: Int, alignment: ZplFieldOrientation? = null) {
    command(FieldOrigin(x = x, y = y, alignment = alignment))
}

/**
 * Draws a graphic field.
 * @param format The format of the graphic field.
 * @param dataBytes The number of data bytes.
 * @param totalBytes The total number of bytes.
 * @param rowBytes The number of bytes per row.
 * @param data The data for the graphic field.
 */
fun ZplBuilder.graphicField(format: Char, dataBytes: Int, totalBytes: Int, rowBytes: Int, data: String) {
    command(GraphicField(
        format = format,
        dataBytes = dataBytes,
        totalBytes = totalBytes,
        rowBytes = rowBytes,
        data = data
    ))
}

/**
 * Adds a field separator.
 */
fun ZplBuilder.fieldSeparator() {
    command(FieldSeparator)
}

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