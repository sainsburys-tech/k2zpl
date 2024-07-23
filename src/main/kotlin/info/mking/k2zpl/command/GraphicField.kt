package info.mking.k2zpl.command

internal data class GraphicField(
    val format: Char,
    val dataBytes: Int,
    val totalBytes: Int,
    val rowBytes: Int,
    val data: String
) : ZplCommand {
    init {
        require(dataBytes in 1..999999) { "Data bytes must be between 1 and 999999" }
        require(totalBytes in 1..999999) { "Total bytes must be between 1 and 999999" }
        require(rowBytes in 1..32000) { "Row bytes must be between 1 and 32000" }
    }

    override val command: CharSequence = "^GF"
    override val parameters: LinkedHashMap<CharSequence, Any?> = linkedMapOf(
        "f" to format, "db" to dataBytes, "tb" to totalBytes, "rb" to rowBytes, "d" to data
    )
}