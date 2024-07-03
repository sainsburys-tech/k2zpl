/**
 * Sets the print width of the label.
 * @param width The width of the label.
 */
fun ZplBuilder.printWidth(width: Int) {
    command(ZplCommand.PrintWidth(width))
}

/**
 * Sets the label home position.
 * @param x The x-coordinate of the label home.
 * @param y The y-coordinate of the label home.
 */
fun ZplBuilder.labelHome(x: Int, y: Int) {
    command(ZplCommand.LabelHome(x, y))
}

/**
 * Sets the label shift.
 * @param shift The shift amount.
 */
fun ZplBuilder.labelShift(shift: Int) {
    command(ZplCommand.LabelShift(shift))
}

/**
 * Sets the font for text fields.
 * @param font The font to use.
 * @param height The height of the font.
 * @param width The width of the font.
 */
fun ZplBuilder.font(font: ZplFont, height: Int, width: Int) {
    command(ZplCommand.Font(font, height, width))
}

/**
 * Sets the font with additional path parameter.
 * @param orientation The orientation of the font.
 * @param height The height of the font.
 * @param width The width of the font.
 * @param path The path to the font file.
 */
fun ZplBuilder.fontAt(orientation: ZplFieldOrientation, height: Int, width: Int, path: String) {
    command(ZplCommand.FontAt(orientation, height, width, path))
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
    command(ZplCommand.FieldBlock(width, lines, lineSpacing, alignment, hangingIndent))
}

/**
 * Creates a Code 128 barcode.
 * @param orientation The orientation of the barcode.
 * @param magnification The magnification factor of the barcode.
 * @param eci The Extended Channel Interpretation value.
 * @param size The size of the barcode.
 * @param readerInit The reader initialization value.
 * @param symbols The number of symbols.
 * @param id The identifier for the barcode.
 */
fun ZplBuilder.code128Barcode(orientation: ZplFieldOrientation, magnification: Int, eci: Int, size: Int, readerInit: Char, symbols: Int, id: Int) {
    command(ZplCommand.Code128Barcode(orientation, magnification, eci, size, readerInit, symbols, id))
}

/**
 * Creates a Code 39 barcode.
 * @param orientation The orientation of the barcode.
 * @param checkDigit Whether to include a check digit.
 * @param height The height of the barcode.
 * @param line The line thickness of the barcode.
 * @param lineAbove Whether to include a line above the barcode.
 */
fun ZplBuilder.code39Barcode(orientation: ZplFieldOrientation, checkDigit: Boolean, height: Int, line: Int, lineAbove: Boolean) {
    command(ZplCommand.Code39Barcode(orientation, checkDigit, height, line, lineAbove))
}

/**
 * Sets the default field for barcodes.
 * @param width The width of the barcode field.
 * @param widthRatio The ratio of the width of the barcode field.
 * @param height The height of the barcode field.
 */
fun ZplBuilder.defaultBarcodeField(width: Int, widthRatio: Int, height: Int) {
    command(ZplCommand.BarcodeFieldDefault(width, widthRatio, height))
}

/**
 * Sets the field orientation for text fields.
 * @param orientation The orientation of the field.
 */
fun ZplBuilder.defaultFieldOrientation(orientation: ZplFieldOrientation) {
    command(ZplCommand.FieldOrientation(orientation))
}

/**
 * Sets the default font for alphanumeric text fields.
 * @param zplFont The font to use.
 * @param height The height of the font.
 * @param width The width of the font.
 */
fun ZplBuilder.defaultAlphanumericFont(font: ZplFont, height: Int, width: Int) {
    command(ZplCommand.ChangeAlphanumericFont(font, height, width))
}

/**
 * Sets the print rate.
 * @param speed The print speed.
 * @param darkness The print darkness.
 */
fun ZplBuilder.defaultPrintRate(speed: ZplPrintSpeed, darkness: Int) {
    command(ZplCommand.PrintRate(speed, darkness))
}

/**
 * Sets the media darkness.
 * @param darkness The darkness level.
 */
fun ZplBuilder.defaultMediaDarkness(darkness: Int) {
    command(ZplCommand.MediaDarkness(darkness))
}

/**
 * Sets the origin for a field.
 * @param x The x-coordinate of the field origin.
 * @param y The y-coordinate of the field origin.
 * @param alignment The alignment of the field (optional).
 */
fun ZplBuilder.fieldOrigin(x: Int, y: Int, alignment: ZplFieldOrientation? = null) {
    command(ZplCommand.FieldOrigin(x, y, alignment))
}

/**
 * Draws a graphic box.
 * @param width The width of the box.
 * @param height The height of the box.
 * @param thickness The thickness of the box border.
 * @param color The color of the box border (optional).
 * @param rounding The corner rounding of the box (optional).
 */
fun ZplBuilder.graphicBox(width: Int, height: Int, thickness: Int, color: ZplLineColor? = null, rounding: Int? = null) {
    command(ZplCommand.GraphicBox(width, height, thickness, color, rounding))
}

/**
 * Draws a graphic circle.
 * @param diameter The diameter of the circle.
 * @param thickness The thickness of the circle border.
 * @param color The color of the circle border (optional).
 */
fun ZplBuilder.graphicCircle(diameter: Int, thickness: Int, color: ZplLineColor? = null) {
    command(ZplCommand.GraphicCircle(diameter, thickness, color))
}

/**
 * Draws a graphic diagonal line.
 * @param width The width of the line.
 * @param height The height of the line.
 * @param thickness The thickness of the line.
 * @param color The color of the line (optional).
 * @param orientation The orientation of the line (optional).
 */
fun ZplBuilder.graphicDiagonalLine(width: Int, height: Int, thickness: Int, color: ZplLineColor? = null, orientation: ZplDiagonalOrientation? = null) {
    command(ZplCommand.GraphicDiagonalLine(width, height, thickness, color, orientation))
}

/**
 * Draws a graphic ellipse.
 * @param width The width of the ellipse.
 * @param height The height of the ellipse.
 * @param thickness The thickness of the ellipse border.
 * @param color The color of the ellipse border (optional).
 */
fun ZplBuilder.graphicEllipse(width: Int, height: Int, thickness: Int, color: ZplLineColor? = null) {
    command(ZplCommand.GraphicEllipse(width, height, thickness, color))
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
    command(ZplCommand.GraphicField(format, dataBytes, totalBytes, rowBytes, data))
}

/**
 * Adds a field separator.
 */
fun ZplBuilder.fieldSeparator() {
    command(ZplCommand.FieldSeparator)
}

/**
 * Adds field data.
 * @param data The data to be added to the field.
 */
fun ZplBuilder.fieldData(data: String) {
    command(ZplCommand.FieldData(data))
}

/**
 * Sets the print quantity and related parameters.
 * @param quantity The number of labels to print.
 * @param labelsBetweenPauses The number of labels between pauses (optional).
 * @param replicates The number of times to replicate the label (optional).
 * @param noPause Whether to pause the printer (optional).
 * @param cutOnError Whether to cut on error (optional).
 */
fun ZplBuilder.printQuantity(quantity: Int, labelsBetweenPauses: Int? = null, replicates: Int? = null, noPause: Boolean = false, cutOnError: Boolean = false) {
    command(ZplCommand.PrintQuantity(quantity, labelsBetweenPauses, replicates, noPause, cutOnError))
}

/**
 * Sets the length of the label.
 * @param length The length of the label.
 */
fun ZplBuilder.labelLength(length: Int) {
    command(ZplCommand.LabelLength(length))
}

/**
 * Starts the label format.
 */
fun ZplBuilder.startFormat() {
    command(ZplCommand.StartFormat)
}

/**
 * Ends the label format.
 */
fun ZplBuilder.endFormat() {
    command(ZplCommand.EndFormat)
}
