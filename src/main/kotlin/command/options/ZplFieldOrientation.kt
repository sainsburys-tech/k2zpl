package command.options

enum class ZplFieldOrientation(val code: Char) {
    NORMAL('N'),
    ROTATED_90('R'),
    INVERTED('I'),
    BOTTOM_UP('B')
}