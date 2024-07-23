package info.mking.k2zpl.command

import info.mking.k2zpl.command.options.ZplFieldOrientation


internal data class Code128Barcode(
    val orientation: ZplFieldOrientation, val magnification: Int, val eci: Int, val size: Int,
    val readerInit: Char, val symbols: Int, val id: Int
) : ZplCommand {
    init {
        require(magnification in 1..10) { "Magnification must be between 1 and 10" }
        require(eci in 0..999999) { "ECI must be between 0 and 999999" }
        require(size in 1..32000) { "Size must be between 1 and 32000" }
        require(symbols in 1..9999) { "Symbols must be between 1 and 9999" }
        require(id in 0..9999) { "ID must be between 0 and 9999" }
    }

    override val command: CharSequence = "^B0"
    override val parameters: LinkedHashMap<CharSequence, Any?> = linkedMapOf(
        "o" to orientation.code, "m" to magnification, "e" to eci, "s" to size,
        "r" to readerInit, "sy" to symbols, "id" to id
    )
}