package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.k2zpl
import com.sainsburys.k2zpl.testBuildString
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class StartFormatTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    describe("StartFormat") {
        it("outputs correct command") {
            StartFormat.testBuildString() shouldBe "^XA"
        }
    }
    describe("startFormat extension function") {
        it("outputs correct command") {
            val result = k2zpl {
                startFormat()
            }
            result shouldBe "^XA\n"
        }
    }
})