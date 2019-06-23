package net.rizov.cetus.runner

import net.rizov.cetus.runner.block.EndCommand

abstract class Token(val value: String) {

  open fun execute(input: Token): Token = EndCommand()

  open fun stringValue() = value
}
