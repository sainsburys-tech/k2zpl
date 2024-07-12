package info.mking.k2zpl

import info.mking.k2zpl.command.ZplCommand

fun ZplCommand.testBuildString() = buildString { build(this) }

fun <S, T> List<T>.repeated(times: Int, block: (T) -> S) = flatMap { item ->
    buildList {
        repeat(times) {
            add(block.invoke(item))
        }
    }
}