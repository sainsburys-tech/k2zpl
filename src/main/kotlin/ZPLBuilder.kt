class ZplBuilder {
    private val commands = mutableListOf<ZplCommand>()
    private var _dpiSetting: DpiSetting = DpiSetting.Unset  // Default DPI value
    private var currentY: Int = 0
    private var defaultFont: DefaultFont = DefaultFont()

    var dpiSetting: DpiSetting
        get() {
            if (_dpiSetting == DpiSetting.Unset) {
                throw IllegalStateException("DPI is not set")
            }
            return _dpiSetting
        }
        set(value) {
            _dpiSetting = value
        }

    fun command(command: ZplCommand) {
        commands.add(command)
    }

    fun command(commandString: String) {
        command(ZplCommand.CustomCommand(commandString))
    }

    fun build(): String {
        val stringBuilder = StringBuilder()
        for (command in commands) {
            val params = command.parameters.values.joinToString(",") { it.toString() }
            stringBuilder.append(command.command).append(params).append('\n')
        }
        return stringBuilder.toString()
    }

    /**
     * Extension property to convert Int centimeters to dots.
     */
    val Int.cm: Int
        get() {
            if (dpiSetting == DpiSetting.Unset) {
                throw IllegalStateException("DPI is not set")
            }
            val cm = this.toDouble()
            return (cm * 10 * dpiSetting.dotsPerMm).toInt()
        }

    /**
     * Extension property to convert Int inches to dots.
     */
    val Int.inches: Int
        get() {
            if (dpiSetting == DpiSetting.Unset) {
                throw IllegalStateException("DPI is not set")
            }
            val inches = this.toDouble()
            val dotsPerInch = dpiSetting.dpi
            return (inches * dotsPerInch).toInt()
        }

    /**
     * Extension property to convert Int millimeters to dots.
     */
    val Int.mm: Int
        get() {
            if (dpiSetting == DpiSetting.Unset) {
                throw IllegalStateException("DPI is not set")
            }
            val mm = this.toDouble()
            return (mm * dpiSetting.dotsPerMm).toInt()
        }

    /**
     * Extension property to return Int value in dots.
     */
    val Int.dots: Int
        get() = this

    /**
     * Sets the default font and font size.
     */
    fun setDefaultFont(font: ZplFont = ZplFont.A, fontHeight: Int = 30, fontWidth: Int = 30) {
        defaultFont = DefaultFont(font, fontHeight, fontWidth)
        font(font, fontHeight, fontWidth) // Set the default font immediately
    }

    /**
     * Adds a field with specified font and size, and updates the current vertical position.
     */
    fun field(x: Int = 0, font: ZplFont, fontHeight: Int, fontWidth: Int, data: String, spacing: Int = 4.mm) {
        fieldOrigin(x, currentY)
        font(font, fontHeight, fontWidth)
        fieldData(data)
        fieldSeparator()
        currentY += fontHeight + spacing
    }

    /**
     * Adds a field with the default font, and updates the current vertical position.
     */
    fun field(x: Int = 0, data: String, spacing: Int = 4.mm) {
        fieldOrigin(x, currentY)
        font(defaultFont.font, defaultFont.fontHeight, defaultFont.fontWidth)
        fieldData(data)
        fieldSeparator()
        currentY += defaultFont.fontHeight + spacing
    }

    /**
     * Sets the initial vertical position.
     */
    fun setInitialVerticalPosition(y: Int) {
        currentY = y
    }
}

data class DefaultFont(
    val font: ZplFont = ZplFont.A,
    val fontHeight: Int = 30,
    val fontWidth: Int = 30
)

enum class DpiSetting(val dpi: Int, val dotsPerMm: Double) {
    Unset(-1, -1.0),
    DPI_152(152, 6.0),
    DPI_203(203, 8.0),
    DPI_300(300, 11.8),
    DPI_608(608, 24.0);

    companion object {
        fun fromDpi(dpi: Int): DpiSetting {
            return entries.find { it.dpi == dpi }
                ?: throw IllegalArgumentException("Unsupported DPI value: $dpi")
        }
    }
}

fun zpl(init: ZplBuilder.() -> Unit) = ZplBuilder().apply {
    init()
}.build()

fun label(init: ZplBuilder.() -> Unit) = ZplBuilder().apply {
    startFormat()
    init()
    endFormat()
}


