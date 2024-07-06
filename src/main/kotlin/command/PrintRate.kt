package command

import command.options.ZplPrintSpeed

internal data class PrintRate(val speed: ZplPrintSpeed, val darkness: Int) : ZplCommand {
    init {
        require(darkness in 0..30) { "Darkness must be between 0 and 30" }
    }

    override val command: String = "^PR"
    override val parameters: Map<String, Any?> = mapOf("s" to speed.code, "d" to darkness)
}