package info.mking.k2zpl.command

import info.mking.k2zpl.k2zpl
import info.mking.k2zpl.testBuildString
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class EndFormatTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    describe("EndFormat") {
        it("outputs correct command") {
            EndFormat.testBuildString() shouldBe "^XZ"
        }
    }
    describe("endFormat extension fucntion") {
        it("outputs correct command") {
            val result = k2zpl {
                endFormat()
            }
            result shouldBe "^XZ\n"
        }
    }
})