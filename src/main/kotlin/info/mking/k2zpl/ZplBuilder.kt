@file:Suppress("UNUSED")

package info.mking.k2zpl

import info.mking.k2zpl.command.CustomCommand
import info.mking.k2zpl.command.ZplCommand
import info.mking.k2zpl.command.options.ZplDpiSetting
import info.mking.k2zpl.command.options.ZplFont
import info.mking.k2zpl.extensions.endFormat
import info.mking.k2zpl.extensions.fieldData
import info.mking.k2zpl.extensions.fieldOrigin
import info.mking.k2zpl.extensions.fieldSeparator
import info.mking.k2zpl.extensions.font
import info.mking.k2zpl.extensions.startFormat

class ZplBuilder {
    private val commands = mutableListOf<ZplCommand>()
    private var _Zpl_dpiSetting: ZplDpiSetting = ZplDpiSetting.Unset  // Default DPI value
    private var defaultFont: DefaultFont = DefaultFont()

    var zplDpiSetting: ZplDpiSetting
        get() {
            if (_Zpl_dpiSetting == ZplDpiSetting.Unset) {
                throw IllegalStateException("DPI is not set")
            }
            return _Zpl_dpiSetting
        }
        set(value) {
            _Zpl_dpiSetting = value
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
            if (zplDpiSetting == ZplDpiSetting.Unset) {
                throw IllegalStateException("DPI is not set")
            }
            val cm = this.toDouble()
            return (cm * 10 * zplDpiSetting.dotsPerMm).toInt()
        }

    /**
     * Extension property to convert Int inches to dots.
     */
    val Int.inches: Int
        get() {
            if (zplDpiSetting == ZplDpiSetting.Unset) {
                throw IllegalStateException("DPI is not set")
            }
            val inches = this.toDouble()
            val dotsPerInch = zplDpiSetting.dpi
            return (inches * dotsPerInch).toInt()
        }

    /**
     * Extension property to convert Int millimeters to dots.
     */
    val Int.mm: Int
        get() {
            if (zplDpiSetting == ZplDpiSetting.Unset) {
                throw IllegalStateException("DPI is not set")
            }
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
        defaultFont = DefaultFont(font, fontHeight, fontWidth)
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
        font(defaultFont.font, defaultFont.fontHeight, defaultFont.fontWidth)
        fieldData(data)
        fieldSeparator()
    }
}

data class DefaultFont(
    val font: ZplFont = ZplFont.A,
    val fontHeight: Int = 30,
    val fontWidth: Int = 30
)

fun zpl(init: ZplBuilder.() -> Unit) = ZplBuilder().apply {
    init()
}.build()

fun label(init: ZplBuilder.() -> Unit) = ZplBuilder().apply {
    startFormat()
    init()
    endFormat()
}


