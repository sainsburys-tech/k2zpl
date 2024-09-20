package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.testBuildString
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ZplCommandTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("build") {
        it("outputs command") {
            ZplCommandWithoutParameters().testBuildString() shouldBe "^ZC"
        }
        it("outputs command with one parameter") {
            ZplCommandWithOneParameter().testBuildString() shouldBe "^ZCPvalue-one"
        }
        it("outputs command with two parameters") {
            ZplCommandWithMultipleParameters().testBuildString() shouldBe "^ZCPSvalue-one,value-two"
        }
        it("does not add null value first parameter") {
            ZplCommandWitNullFirstParameter().testBuildString() shouldBe "^ZCPNvalue-two"
        }
        it("does not add null value second parameter") {
            ZplCommandWitNullSecondParameter().testBuildString() shouldBe "^ZCPNSvalue-one"
        }
    }
})

class ZplCommandWithoutParameters : ZplCommand() {
    override val command = "^ZC"
}

class ZplCommandWithOneParameter : ZplCommand(
    parameters = listOf("param-one" to "value-one")
) {
    override val command = "^ZCP"
}

class ZplCommandWithMultipleParameters : ZplCommand(
    parameters = listOf(
        "param-one" to "value-one",
        "param-two" to "value-two"
    )
) {
    override val command = "^ZCPS"
}

class ZplCommandWitNullFirstParameter : ZplCommand(
    parameters = listOf(
        "param-one" to null,
        "param-two" to "value-two"
    )
) {
    override val command = "^ZCPN"
}

class ZplCommandWitNullSecondParameter : ZplCommand(
    parameters = listOf(
        "param-one" to "value-one",
        "param-two" to null
    )
) {
    override val command = "^ZCPNS"
}