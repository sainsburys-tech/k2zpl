@file:Suppress("UNUSED")

package info.mking.k2zpl.builder

import info.mking.k2zpl.command.CustomCommand
import info.mking.k2zpl.command.Font
import info.mking.k2zpl.command.ZplCommand
import info.mking.k2zpl.command.fieldOrigin
import info.mking.k2zpl.command.font
import info.mking.k2zpl.command.options.ZplDpiSetting
import info.mking.k2zpl.command.options.ZplFieldOrientation
import info.mking.k2zpl.command.options.ZplFont
import kotlin.math.roundToInt

class ZplBuilder {
    private val commands = mutableListOf<ZplCommand>()
    private var _zplDpiSetting: ZplDpiSetting = ZplDpiSetting.Unset
    private var defaultFont: Font = Font(ZplFont.A, ZplFieldOrientation.NORMAL, 30.dots, 30.dots)

    var dpiSetting: ZplDpiSetting
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

    fun build() = buildString {
        commands.forEach { it.build(this@buildString).append('\n') }
    }

    /**
     * Extension property to convert Int centimeters to dots.
     */
    val Int.cm: Int
        get() {
            requireDpiSetting()
            return (toDouble() * 10 * dpiSetting.dotsPerMm).roundToInt()
        }

    /**
     * Extension property to convert Int inches to dots.
     */
    val Int.inches: Int
        get() {
            requireDpiSetting()
            return (toDouble() * dpiSetting.dpi).roundToInt()
        }

    /**
     * Extension property to convert Int millimeters to dots.
     */
    val Int.mm: Int
        get() {
            requireDpiSetting()
            return (toDouble() * dpiSetting.dotsPerMm).roundToInt()
        }

    /**
     * Extension property to return Int value in dots.
     */
    val Int.dots: Int
        get() = this

    /**
     * Sets the default font and font size.
     */
    fun setDefaultFont(
        font: ZplFont = ZplFont.A,
        orientation: ZplFieldOrientation = ZplFieldOrientation.NORMAL,
        fontHeight: Int = 30,
        fontWidth: Int = 30
    ) {
        defaultFont = Font(font, orientation, fontHeight, fontWidth)
        font(font, orientation, fontHeight, fontWidth) // Set the default font immediately
    }

    /**
     * Adds a field with specified font and size.
     */
    fun addField(x: Int, y: Int, font: ZplFont, fontHeight: Int, fontWidth: Int, data: String) {
        fieldOrigin(x, y)
        font(font, defaultFont.orientation, fontHeight, fontWidth)
        fieldData(data)
        fieldSeparator()
    }

    /**
     * Adds a field with the default font.
     */
    fun addField(x: Int = 0, y: Int = 0, data: String) {
        fieldOrigin(x, y)
        font(defaultFont.font, defaultFont.orientation, defaultFont.height, defaultFont.width)
        fieldData(data)
        fieldSeparator()
    }

    /**
     * Enforce DPI setting to be anything but [ZplDpiSetting.Unset]
     * @throws IllegalStateException
     */
    private fun requireDpiSetting() {
        if (dpiSetting == ZplDpiSetting.Unset) {
            throw IllegalStateException("DPI is not set")
        }
    }
}