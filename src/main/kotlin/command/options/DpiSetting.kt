package command.options

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