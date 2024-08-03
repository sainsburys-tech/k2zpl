package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.command.options.ZplMediaMode
import com.sainsburys.k2zpl.command.options.ZplYesNo
import com.sainsburys.k2zpl.k2zpl
import com.sainsburys.k2zpl.testBuildString
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MediaModeTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    val mediaMode = MediaMode(mediaMode = ZplMediaMode.CUTTER, prePeelSelect = ZplYesNo.NO)

    describe("MediaMode") {
        it("outputs correct command") {
            mediaMode.testBuildString() shouldBe "^MMC,N"
        }
        it("uses mediaMode parameter properly") {
            ZplMediaMode.entries.forEach {
                mediaMode.copy(mediaMode = it).testBuildString() shouldBe "^MM${it.value},N"
            }
        }
        it("uses prePeelSelect parameter properly") {
            ZplYesNo.entries.forEach {
                mediaMode.copy(prePeelSelect = it).testBuildString() shouldBe "^MMC,${it}"
            }
        }
    }
    describe("mediaMode extension function") {
        it("outputs correct command") {
            val result = k2zpl {
                mediaMode(mediaMode = ZplMediaMode.DELAYED_CUT, prePeel = true)
            }
            result shouldBe "^MMD,Y\n"
        }
        it("uses default values") {
            val result = k2zpl {
                mediaMode(mediaMode = ZplMediaMode.REWIND)
            }
            result shouldBe "^MMR,N\n"
        }
    }
})