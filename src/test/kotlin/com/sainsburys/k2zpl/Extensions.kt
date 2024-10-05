package com.sainsburys.k2zpl

import com.sainsburys.k2zpl.command.ZplCommand
import io.kotest.data.row
import kotlin.enums.EnumEntries

fun ZplCommand.testBuildString() = buildString { build(this) }

inline fun <reified T : Enum<T>> EnumEntries<T>.toRows() = map(::row)