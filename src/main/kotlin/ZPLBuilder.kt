class ZplBuilder {
    private val commands = mutableListOf<ZplCommand>()
    private var _dpiSetting: DpiSetting = DpiSetting.Unset  // Default DPI value
    private var currentY: Int = 0

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

    fun addCommand(command: ZplCommand) {
        commands.add(command)
    }

    fun build(): String {
        return commands.joinToString("\n") { command ->
            val params = command.parameters.values.joinToString(",") { it.toString() }
            "${command.command}$params"
        }
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
        get() {
            if (dpiSetting == DpiSetting.Unset) {
                throw IllegalStateException("DPI is not set")
            }
            return this
        }

    /**
     * Adds a field and updates the current vertical position.
     */
    fun addField(x: Int, font: ZplFont, fontHeight: Int, fontWidth: Int, data: String, spacing: Int = 4.mm) {
        fieldOrigin(x, currentY)
        font(font, fontHeight, fontWidth)
        fieldData(data)
        fieldSeparator()
        currentY += fontHeight + spacing
    }
}


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


fun zplScript(init: ZplBuilder.() -> Unit): String {
    val builder = ZplBuilder()
    builder.init()
    return builder.build()
}


