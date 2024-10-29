package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.command.options.ZplMediaType
import com.sainsburys.k2zpl.k2zpl
import com.sainsburys.k2zpl.testBuildString
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class MediaTypeTest : DescribeSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    describe("MediaType") {

        val subject = MediaType(type = ZplMediaType.DIRECT_TRANSFER)

        it("outputs correct command") {
            table(
                headers("media type"),
                ZplMediaType.entries.map { row(it) }
            ).forAll { type ->
                subject.copy(type = type).testBuildString() shouldBe "^MT${type}"
            }
        }
    }
    describe("mediaType extension function") {
        it("outputs correct command") {
            table(
                headers("media type"),
                ZplMediaType.entries.map { row(it) }
            ).forAll { type ->
                val result = k2zpl {
                    mediaType(type)
                }
                result shouldBe "^MT${type}\n"
            }
        }
    }
})
