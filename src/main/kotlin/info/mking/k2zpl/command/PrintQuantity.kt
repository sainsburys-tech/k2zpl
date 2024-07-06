package info.mking.k2zpl.command

internal data class PrintQuantity(
    val quantity: Int, val labelsBetweenPauses: Int? = null, val replicates: Int? = null,
    val noPause: Boolean = false, val cutOnError: Boolean = false
) : ZplCommand {
    init {
        require(quantity in 1..999999) { "Quantity must be between 1 and 999999" }
        if (labelsBetweenPauses != null) require(labelsBetweenPauses in 1..9999) { "Labels between pauses must be between 1 and 9999" }
        if (replicates != null) require(replicates in 1..9999) { "Replicates must be between 1 and 9999" }
    }

    override val command: String = "^PQ"
    override val parameters: Map<String, Any?> = mutableMapOf<String, Any?>("q" to quantity).apply {
        if (labelsBetweenPauses != null) this["lbp"] = labelsBetweenPauses
        if (replicates != null) this["r"] = replicates
        if (noPause) this["np"] = noPause.toString()
        if (cutOnError) this["coe"] = cutOnError.toString()
    }
}