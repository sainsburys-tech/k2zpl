package command

internal data class LabelHome(val x: Int, val y: Int) : ZplCommand {
    override val command: String = "^LH"
    override val parameters: Map<String, Any?> = mapOf("x" to x, "y" to y)
}