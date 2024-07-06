@file:Suppress("UNUSED")

package info.mking.k2zpl.builder

import info.mking.k2zpl.command.CustomCommand
import info.mking.k2zpl.command.Font
import info.mking.k2zpl.command.ZplCommand
import info.mking.k2zpl.command.options.ZplDpiSetting
import info.mking.k2zpl.command.options.ZplFont

class ZplBuilder {
    private val commands = mutableListOf<ZplCommand>()
    private var _zplDpiSetting: ZplDpiSetting = ZplDpiSetting.Unset
    private var defaultFont: Font = Font(ZplFont.A, 30.dots, 30.dots)

    var zplDpiSetting: ZplDpiSetting
        get() {
            if (_zplDpiSetting == ZplDpiSetting.Unset) {
                throw IllegalStateException("DPI is not set")
            }
            return _zplDpiSetting
        }
        set(value) {
            _zplDpiSetting = value
        }

    fun addCommand(command: ZplCommand) {
        commands.add(command)
    }

    fun addCommand(commandString: String) {
        addCommand(CustomCommand(commandString))
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
            requireZplDpiSetting()
            val cm = this.toDouble()
            return (cm * 10 * zplDpiSetting.dotsPerMm).toInt()
        }

    /**
     * Extension property to convert Int inches to dots.
     */
    val Int.inches: Int
        get() {
            requireZplDpiSetting()
            val inches = this.toDouble()
            val dotsPerInch = zplDpiSetting.dpi
            return (inches * dotsPerInch).toInt()
        }

    /**
     * Extension property to convert Int millimeters to dots.
     */
    val Int.mm: Int
        get() {
            requireZplDpiSetting()
            val mm = this.toDouble()
            return (mm * zplDpiSetting.dotsPerMm).toInt()
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
        defaultFont = Font(font, fontHeight, fontWidth)
        font(font, fontHeight, fontWidth) // Set the default font immediately
    }

    /**
     * Adds a field with specified font and size.
     */
    fun addField(x: Int, y: Int, font: ZplFont, fontHeight: Int, fontWidth: Int, data: String) {
        fieldOrigin(x, y)
        font(font, fontHeight, fontWidth)
        fieldData(data)
        fieldSeparator()
    }

    /**
     * Adds a field with the default font.
     */
    fun addField(x: Int = 0, y: Int = 0, data: String) {
        fieldOrigin(x, y)
        font(defaultFont.font, defaultFont.height, defaultFont.width)
        fieldData(data)
        fieldSeparator()
    }

    /**
     * Enforce DPI setting to be anything but [ZplDpiSetting.Unset]
     * @throws IllegalStateException
     */
    private fun requireZplDpiSetting() {
        if (zplDpiSetting == ZplDpiSetting.Unset) {
            throw IllegalStateException("DPI is not set")
        }
    }
}
