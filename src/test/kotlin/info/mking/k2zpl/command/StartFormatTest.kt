package info.mking.k2zpl.command

import info.mking.k2zpl.k2zpl
import info.mking.k2zpl.testBuildString
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