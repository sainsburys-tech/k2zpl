package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.k2zpl
import com.sainsburys.k2zpl.testBuildString
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class LabelHomeTest : DescribeSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    val labelHome = LabelHome(
        x = 10,
        y = 10
    )

    describe("LabelHome") {
        it("outputs the correct command") {
            labelHome.testBuildString() shouldBe "^LH10,10"
        }
    }
    describe("labelHome extension function") {
        it("outputs the correct command") {
            val result = k2zpl {
                labelHome(x = 0, y = 100)
            }
            result shouldBe "^LH0,100\n"
        }
    }
})