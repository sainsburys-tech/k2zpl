package info.mking.k2zpl.command

internal data class PrintQuantity(
    val quantity: Int,
    val labelsBetweenPauses: Int? = null,
    val replicates: Int? = null,
    val noPause: Boolean = false,
    val cutOnError: Boolean = false
) : ZplCommand {
    init {
        require(quantity in 1..999999) { "Quantity must be between 1 and 999999" }
        if (labelsBetweenPauses != null) require(labelsBetweenPauses in 1..9999) { "Labels between pauses must be between 1 and 9999" }
        if (replicates != null) require(replicates in 1..9999) { "Replicates must be between 1 and 9999" }
    }

    override val command: CharSequence = "^PQ"
    override val parameters: LinkedHashMap<CharSequence, Any?> = buildLinkedMap {
        put("q", quantity)
        if (labelsBetweenPauses != null) put("lbp", labelsBetweenPauses)
        if (replicates != null) put("r", replicates)
        if (noPause) put("np", noPause.toString())
        if (cutOnError) put("coe", cutOnError.toString())
    }
}