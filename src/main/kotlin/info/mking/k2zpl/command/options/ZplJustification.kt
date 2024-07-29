package info.mking.k2zpl.command.options

enum class ZplJustification(private val value: Char) {
    LEFT('0'),
    RIGHT('1'),
    AUTO('2');

    override fun toString(): String {
        return value.toString()
    }
}