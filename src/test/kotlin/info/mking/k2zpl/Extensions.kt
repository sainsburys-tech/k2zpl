package info.mking.k2zpl

import info.mking.k2zpl.command.ZplCommand

fun ZplCommand.testBuildString() = buildString { build(this) }

private fun <T> Iterator<T>.nextNotNull(block: (T) -> Unit) {
    next()?.let { block(it) }
}
