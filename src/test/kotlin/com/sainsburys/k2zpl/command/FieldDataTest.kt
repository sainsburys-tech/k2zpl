package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.k2zpl
import com.sainsburys.k2zpl.testBuildString
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
        it("outputs correct command using raw string") {
            val result = k2zpl {
                val data =
                    """
                    some data
                    with line breaks
                
                """.trimIndent()
                fieldData(data = data)
            }
            result shouldBe "^FDsome data\\&with line breaks\\&\n"
        }
        it("outputs correct command using string") {
            val result = k2zpl {
                fieldData(data = "some data\nwith line breaks\n")
            }
            result shouldBe "^FDsome data\\&with line breaks\\&\n"
        }
    }
})