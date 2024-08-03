package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.command.options.ZplCompressionType
import com.sainsburys.k2zpl.k2zpl
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class GraphicFieldTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    describe("GraphicField") {

    }
    describe("graphicField extension function") {
        it("outputs correct command") {
            val result = k2zpl {
                graphicField(
                    format = ZplCompressionType.BINARY,
                    dataBytes = 1024,
                    totalBytes = 2048,
                    rowBytes = 10000,
                    data = "data"
                )
            }
            result shouldBe "^GFB,1024,2048,10000,data\n"
        }
    }
})