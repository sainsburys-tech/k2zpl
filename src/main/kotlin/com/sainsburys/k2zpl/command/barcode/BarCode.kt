package com.sainsburys.k2zpl.command.barcode

import com.sainsburys.k2zpl.command.ZplCommand
import com.sainsburys.k2zpl.command.options.ZplFieldOrientation

internal interface BarCode : ZplCommand {
    val orientation: ZplFieldOrientation
    val height: Int
}