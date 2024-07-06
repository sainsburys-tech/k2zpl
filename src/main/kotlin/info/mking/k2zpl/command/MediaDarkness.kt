package info.mking.k2zpl.command

internal data class MediaDarkness(val darkness: Int) : ZplCommand {
    init {
        require(darkness in 0..30) { "Darkness must be between 0 and 30" }
    }

    override val command: String = "^MD"
    override val parameters: Map<String, Any?> = mapOf("d" to darkness)
}