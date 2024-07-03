sealed class ZplCommand {
    abstract val command: String
    abstract val parameters: Map<String, Any?>

    data class PrintWidth(val width: Int) : ZplCommand() {
        init {
            require(width in 1..32000) { "Width must be between 1 and 32000" }
        }
        override val command: String = "^PW"
        override val parameters: Map<String, Any?> = mapOf("w" to width)
    }

    data class Font(val font: ZplFont, val height: Int, val width: Int) : ZplCommand() {
        init {
            require(height in 10..32000) { "Height must be between 10 and 32000" }
            require(width in 10..32000) { "Width must be between 10 and 32000" }
        }
        override val command: String = "^A"
        override val parameters: Map<String, Any?> = mapOf("f" to font.code, "h" to height, "w" to width)
    }

    data class FontAt(val orientation: ZplFieldOrientation, val height: Int, val width: Int, val path: String) : ZplCommand() {
        init {
            require(height in 10..32000) { "Height must be between 10 and 32000" }
            require(width in 10..32000) { "Width must be between 10 and 32000" }
        }
        override val command: String = "^A@"
        override val parameters: Map<String, Any?> = mapOf("o" to orientation.code, "h" to height, "w" to width, "p" to path)
    }

    data class Code128Barcode(
        val orientation: ZplFieldOrientation, val magnification: Int, val eci: Int, val size: Int,
        val readerInit: Char, val symbols: Int, val id: Int
    ) : ZplCommand() {
        init {
            require(magnification in 1..10) { "Magnification must be between 1 and 10" }
            require(eci in 0..999999) { "ECI must be between 0 and 999999" }
            require(size in 1..32000) { "Size must be between 1 and 32000" }
            require(symbols in 1..9999) { "Symbols must be between 1 and 9999" }
            require(id in 0..9999) { "ID must be between 0 and 9999" }
        }
        override val command: String = "^B0"
        override val parameters: Map<String, Any?> = mapOf(
            "o" to orientation.code, "m" to magnification, "e" to eci, "s" to size,
            "r" to readerInit, "sy" to symbols, "id" to id
        )
    }

    data class Code39Barcode(
        val orientation: ZplFieldOrientation, val checkDigit: Boolean, val height: Int, val line: Int, val lineAbove: Boolean
    ) : ZplCommand() {
        init {
            require(height in 1..32000) { "Height must be between 1 and 32000" }
            require(line in 1..7) { "Line thickness must be between 1 and 7" }
        }
        override val command: String = "^B1"
        override val parameters: Map<String, Any?> = mapOf(
            "o" to orientation.code, "c" to checkDigit.toString(), "h" to height,
            "l" to line, "la" to lineAbove.toString()
        )
    }

    data class BarcodeFieldDefault(val width: Int, val widthRatio: Int, val height: Int) : ZplCommand() {
        init {
            require(width in 1..10) { "Width must be between 1 and 10" }
            require(widthRatio in 2..3) { "Width ratio must be 2 or 3" }
            require(height in 1..32000) { "Height must be between 1 and 32000" }
        }
        override val command: String = "^BY"
        override val parameters: Map<String, Any?> = mapOf("w" to width, "r" to widthRatio, "h" to height)
    }

    data class FieldOrientation(val orientation: ZplFieldOrientation) : ZplCommand() {
        override val command: String = "^FW"
        override val parameters: Map<String, Any?> = mapOf("o" to orientation.code)
    }

    data class ChangeAlphanumericFont(val font: ZplFont, val height: Int, val width: Int) : ZplCommand() {
        init {
            require(height in 10..32000) { "Height must be between 10 and 32000" }
            require(width in 10..32000) { "Width must be between 10 and 32000" }
        }
        override val command: String = "^CF"
        override val parameters: Map<String, Any?> = mapOf("f" to font.code, "h" to height, "w" to width)
    }

    data class PrintRate(val speed: ZplPrintSpeed, val darkness: Int) : ZplCommand() {
        init {
            require(darkness in 0..30) { "Darkness must be between 0 and 30" }
        }
        override val command: String = "^PR"
        override val parameters: Map<String, Any?> = mapOf("s" to speed.code, "d" to darkness)
    }

    data class MediaDarkness(val darkness: Int) : ZplCommand() {
        init {
            require(darkness in 0..30) { "Darkness must be between 0 and 30" }
        }
        override val command: String = "^MD"
        override val parameters: Map<String, Any?> = mapOf("d" to darkness)
    }

    data class LabelHome(val x: Int, val y: Int) : ZplCommand() {
        override val command: String = "^LH"
        override val parameters: Map<String, Any?> = mapOf("x" to x, "y" to y)
    }

    data class LabelLength(val length: Int) : ZplCommand() {
        init {
            require(length in 1..32000) { "Length must be between 1 and 32000" }
        }
        override val command: String = "^LL"
        override val parameters: Map<String, Any?> = mapOf("l" to length)
    }

    data class LabelShift(val shift: Int) : ZplCommand() {
        override val command: String = "^LS"
        override val parameters: Map<String, Any?> = mapOf("s" to shift)
    }

    // Other commands
    data class FieldOrigin(val x: Int, val y: Int, val alignment: ZplFieldOrientation? = null) : ZplCommand() {
        override val command: String = "^FO"
        override val parameters: Map<String, Any?> = mutableMapOf<String, Any?>("x" to x, "y" to y).apply {
            if (alignment != null) this["a"] = alignment.code
        }
    }
    data class GraphicBox(
        val width: Int, val height: Int, val thickness: Int, val color: ZplLineColor? = null, val rounding: Int? = null
    ) : ZplCommand() {
        init {
            require(width in 1..32000) { "Width must be between 1 and 32000" }
            require(height in 1..32000) { "Height must be between 1 and 32000" }
            require(thickness in 1..32000) { "Thickness must be between 1 and 32000" }
        }
        override val command: String = "^GB"
        override val parameters: Map<String, Any?> = mutableMapOf<String, Any?>("w" to width, "h" to height, "t" to thickness).apply {
            if (color != null) this["c"] = color.code
            if (rounding != null) this["r"] = rounding
        }
    }

    data class GraphicCircle(val diameter: Int, val thickness: Int, val color: ZplLineColor? = null) : ZplCommand() {
        init {
            require(diameter in 3..4095) { "Diameter must be between 3 and 4095" }
            require(thickness in 2..4095) { "Thickness must be between 2 and 4095" }
        }
        override val command: String = "^GC"
        override val parameters: Map<String, Any?> = mutableMapOf<String, Any?>("d" to diameter, "t" to thickness).apply {
            if (color != null) this["c"] = color.code
        }
    }

    data class GraphicDiagonalLine(
        val width: Int, val height: Int, val thickness: Int, val color: ZplLineColor? = null, val orientation: ZplDiagonalOrientation? = null
    ) : ZplCommand() {
        init {
            require(width in 3..32000) { "Width must be between 3 and 32000" }
            require(height in 3..32000) { "Height must be between 3 and 32000" }
            require(thickness in 1..32000) { "Thickness must be between 1 and 32000" }
        }
        override val command: String = "^GD"
        override val parameters: Map<String, Any?> = mutableMapOf<String, Any?>("w" to width, "h" to height, "t" to thickness).apply {
            if (color != null) this["c"] = color.code
            if (orientation != null) this["o"] = orientation.code
        }
    }

    data class GraphicEllipse(val width: Int, val height: Int, val thickness: Int, val color: ZplLineColor? = null) : ZplCommand() {
        init {
            require(width in 3..4095) { "Width must be between 3 and 4095" }
            require(height in 3..4095) { "Height must be between 3 and 4095" }
            require(thickness in 2..4095) { "Thickness must be between 2 and 4095" }
        }
        override val command: String = "^GE"
        override val parameters: Map<String, Any?> = mutableMapOf<String, Any?>("w" to width, "h" to height, "t" to thickness).apply {
            if (color != null) this["c"] = color.code
        }
    }

    data class GraphicField(val format: Char, val dataBytes: Int, val totalBytes: Int, val rowBytes: Int, val data: String) : ZplCommand() {
        init {
            require(dataBytes in 1..999999) { "Data bytes must be between 1 and 999999" }
            require(totalBytes in 1..999999) { "Total bytes must be between 1 and 999999" }
            require(rowBytes in 1..32000) { "Row bytes must be between 1 and 32000" }
        }
        override val command: String = "^GF"
        override val parameters: Map<String, Any?> = mapOf(
            "f" to format, "db" to dataBytes, "tb" to totalBytes, "rb" to rowBytes, "d" to data
        )
    }

    data object FieldSeparator : ZplCommand() {
        override val command: String = "^FS"
        override val parameters: Map<String, Any?> = emptyMap()
    }

    data class FieldData(val data: String) : ZplCommand() {
        override val command: String = "^FD"
        override val parameters: Map<String, Any?> = mapOf("d" to data)
    }

    data class FieldBlock(
        val width: Int, val lines: Int, val lineSpacing: Int, val alignment: ZplTextAlignment, val hangingIndent: Int
    ) : ZplCommand() {
        init {
            require(width in 1..32000) { "Width must be between 1 and 32000" }
            require(lines in 1..999) { "Lines must be between 1 and 999" }
            require(lineSpacing in 0..32000) { "Line spacing must be between 0 and 32000" }
            require(hangingIndent in 0..32000) { "Hanging indent must be between 0 and 32000" }
        }
        override val command: String = "^FB"
        override val parameters: Map<String, Any?> = mapOf(
            "w" to width, "l" to lines, "s" to lineSpacing, "a" to alignment.code, "h" to hangingIndent
        )
    }

    data class PrintQuantity(
        val quantity: Int, val labelsBetweenPauses: Int? = null, val replicates: Int? = null,
        val noPause: Boolean = false, val cutOnError: Boolean = false
    ) : ZplCommand() {
        init {
            require(quantity in 1..999999) { "Quantity must be between 1 and 999999" }
            if (labelsBetweenPauses != null) require(labelsBetweenPauses in 1..9999) { "Labels between pauses must be between 1 and 9999" }
            if (replicates != null) require(replicates in 1..9999) { "Replicates must be between 1 and 9999" }
        }
        override val command: String = "^PQ"
        override val parameters: Map<String, Any?> = mutableMapOf<String, Any?>("q" to quantity).apply {
            if (labelsBetweenPauses != null) this["lbp"] = labelsBetweenPauses
            if (replicates != null) this["r"] = replicates
            if (noPause) this["np"] = noPause.toString()
            if (cutOnError) this["coe"] = cutOnError.toString()
        }
    }

    data object StartFormat : ZplCommand() {
        override val command: String = "^XA"
        override val parameters: Map<String, Any?> = emptyMap()
    }

    data object EndFormat : ZplCommand() {
        override val command: String = "^XZ"
        override val parameters: Map<String, Any?> = emptyMap()
    }

    data class CustomCommand(override val command: String) : ZplCommand() {
        override val parameters: Map<String, Any?> = emptyMap()
    }

    data class MediaModeCommand(val mediaMode: ZplMediaMode, val preprintedLabelHandling: ZplPreprintedLabelHandling) : ZplCommand() {
        override val command: String = "^MM"
        override val parameters: Map<String, Any?> =
            mapOf("mode" to mediaMode.value, "preprinted" to preprintedLabelHandling.value)
    }
}

enum class ZplMediaMode(val value: Char) {
    TEAR_OFF('T'),
    PEEL_OFF('P'),
    REWIND('R'),
    APPLICATOR('A'),
    CUTTER('C'),
    DELAYED_CUT('D')
}

enum class ZplPreprintedLabelHandling(val value: Char) {
    NORMAL('N'),
    PREPRINTED('Y')
}

enum class ZplFieldOrientation(val code: Char) {
    NORMAL('N'),
    ROTATED_90('R'),
    INVERTED('I'),
    BOTTOM_UP('B')
}

enum class ZplFont(val code: Char) {
    A('A'),
    B('B'),
    C('C'),
    D('D'),
    E('E'),
    F('F'),
    G('G'),
    H('H'),
    GS('0') // Graphic Symbol font
}

enum class ZplPrintSpeed(val code: Int) {
    SPEED_2(2),
    SPEED_3(3),
    SPEED_4(4),
    SPEED_5(5),
    SPEED_6(6),
    SPEED_7(7),
    SPEED_8(8),
    SPEED_9(9),
    SPEED_10(10)
}

enum class ZplDiagonalOrientation(val code: Char) {
    RIGHT_LEANING('/'),
    LEFT_LEANING('\\')
}

enum class ZplLineColor(val code: Char) {
    BLACK('B'),
    WHITE('W')
}

enum class ZplTextAlignment(val code: Char) {
    LEFT('L'),
    CENTER('C'),
    RIGHT('R'),
    JUSTIFY('J')
}