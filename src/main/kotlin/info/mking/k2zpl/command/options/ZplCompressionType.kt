@file:Suppress("UNUSED")

package info.mking.k2zpl.command.options

enum class ZplCompressionType(private val value: Char) {
    ASCII('A'),
    BINARY('B'),
    COMPRESSED_BINARY('C');

    override fun toString(): String {
        return value.toString()
    }
}