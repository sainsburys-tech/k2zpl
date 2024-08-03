@file:Suppress("UNUSED")

package com.sainsburys.k2zpl.builder

import com.sainsburys.k2zpl.command.options.ZplYesNo

internal fun Boolean.toZplYesNo(): ZplYesNo = when (this) {
    true -> ZplYesNo.YES
    else -> ZplYesNo.NO
}