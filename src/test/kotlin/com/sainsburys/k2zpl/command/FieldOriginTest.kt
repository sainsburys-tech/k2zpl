package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.command.options.ZplJustification
import com.sainsburys.k2zpl.k2zpl
import com.sainsburys.k2zpl.testBuildString
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class FieldOriginTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    val fieldOrigin = FieldOrigin(
        x = 10,
        y = 10,
        justification = ZplJustification.LEFT
    )

    describe("FieldOrigin") {
        it("outputs correct command") {
            fieldOrigin.testBuildString() shouldBe "^FO10,10,0"
        }
        it("uses alignment parameter correctly") {
            ZplJustification.entries.forEach {
                fieldOrigin.copy(justification = it).testBuildString() shouldBe "^FO10,10,$it"
            }
        }
        it("requires valid parameters") {
            table(
                headers("x", "y"),
                row(-1, 0),
                row(32001, 0),
                row(0, -1),
                row(0, 32001)
            ).forAll { x, y ->
                shouldThrow<IllegalArgumentException> {
                    fieldOrigin.copy(x = x, y = y)
                }
            }
        }
    }
    describe("fieldOrigin extension function") {
        it("outputs correct command") {
            val result = k2zpl {
                fieldOrigin(x = 10, y = 10, justification = ZplJustification.RIGHT)
            }
            result shouldBe "^FO10,10,1\n"
        }
        it("outputs correct command with default parameters") {
            val result = k2zpl {
                fieldOrigin(x = 20, y = 20)
            }
            result shouldBe "^FO20,20,0\n"
        }
    }
})