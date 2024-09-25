package com.sainsburys.k2zpl.command

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ZplParametersTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    describe("ZplParameters pairs constructor") {
        val result = ZplParameters("a" to "b", "c" to "d")
        it("returns values passed to ZplParameters") {
            result.forEachIndexed { index, param ->
                when (index) {
                    0 -> param.value shouldBe "b"
                    1 -> param.value shouldBe "d"
                }
            }
        }
        it("makes values accessible by get operator") {
            result["a"] shouldBe "b"
            result["c"] shouldBe "d"
        }
    }
    describe("ZplParameters map constructor") {
        val result = ZplParameters(mapOf("a" to "b", "c" to "d"))
        it("returns values passed to ZplParameters") {
            result.forEachIndexed { index, param ->
                when (index) {
                    0 -> param.value shouldBe "b"
                    1 -> param.value shouldBe "d"
                }
            }
        }
        it("makes values accessible by get operator") {
            result["a"] shouldBe "b"
            result["c"] shouldBe "d"
        }
    }
    describe("zplParameters pairs function") {
        val result = zplParameters("e" to "f", "g" to "h")
        it("returns values passed to ZplParameters") {
            result.forEachIndexed { index, param ->
                when (index) {
                    0 -> param.value shouldBe "f"
                    1 -> param.value shouldBe "h"
                }
            }        }
        it("makes values accessible by get operator") {
            result["e"] shouldBe "f"
            result["g"] shouldBe "h"
        }
    }

    describe("ZplParameters builder function") {
        val result = zplParameters {
            put("a", "b")
            put("c", "d")
        }
        it("returns values passed to ZplParameters") {
            result.forEachIndexed { index, param ->
                when (index) {
                    0 -> param.value shouldBe "b"
                    1 -> param.value shouldBe "d"
                }
            }
        }
        it("makes values accessible by get operator") {
            result["a"] shouldBe "b"
            result["c"] shouldBe "d"
        }
    }
})