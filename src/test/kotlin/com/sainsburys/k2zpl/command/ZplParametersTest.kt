package com.sainsburys.k2zpl.command

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ZplParametersTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    describe("ZplParameters") {
        val result = ZplParameters("a" to "b", "c" to "d")
        it("returns values passed to ZplParameters") {
            result.values shouldBe listOf("b", "d")
        }
        it("makes values accessible by get operator") {
            result["a"] shouldBe "b"
            result["c"] shouldBe "d"
        }
    }
    describe("zplParameters function") {
        val result = zplParameters("e" to "f", "g" to "h")
        it("returns values passed to ZplParameters") {
            result.values shouldBe listOf("f", "h")
        }
        it("makes values accessible by get operator") {
            result["e"] shouldBe "f"
            result["g"] shouldBe "h"
        }
    }
})