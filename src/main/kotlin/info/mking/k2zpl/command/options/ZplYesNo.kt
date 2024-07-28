package info.mking.k2zpl.command.options

enum class ZplYesNo(val value: String) {
    YES("Y"),
    NO("N");

    override fun toString(): String {
        return value
    }
}