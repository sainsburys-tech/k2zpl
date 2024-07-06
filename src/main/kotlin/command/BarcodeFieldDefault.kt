package command

internal data class BarcodeFieldDefault(val width: Int, val widthRatio: Int, val height: Int) : ZplCommand {
    init {
        require(width in 1..10) { "Width must be between 1 and 10" }
        require(widthRatio in 2..3) { "Width ratio must be 2 or 3" }
        require(height in 1..32000) { "Height must be between 1 and 32000" }
    }

    override val command: String = "^BY"
    override val parameters: Map<String, Any?> = mapOf("w" to width, "r" to widthRatio, "h" to height)
}