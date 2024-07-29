package info.mking.k2zpl.command

import info.mking.k2zpl.k2zpl
import info.mking.k2zpl.testBuildString
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class FieldDataTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    val fieldData = FieldData("some-data")

    describe("FieldData") {
        it("outputs correct command") {
            fieldData.testBuildString() shouldBe "^FDsome-data"
        }
    }
    describe("fieldData extension function") {
        it("outputs correct command") {
            val result = k2zpl {
                fieldData("some-other-data")
            }
            result shouldBe "^FDsome-other-data\n"
        }
    }
})