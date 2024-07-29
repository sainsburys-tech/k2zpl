@file:Suppress("UNUSED")

package info.mking.k2zpl.builder

import info.mking.k2zpl.command.options.ZplYesNo

internal fun Boolean.toZplYesNo(): ZplYesNo = when (this) {
    true -> ZplYesNo.YES
    else -> ZplYesNo.NO
}