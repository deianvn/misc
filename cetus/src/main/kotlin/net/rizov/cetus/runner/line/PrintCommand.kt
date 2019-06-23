package net.rizov.cetus.runner.line

import net.rizov.cetus.runner.Token

class PrintCommand: Token("print") {

  override fun execute(input: Token): Token {
    println(input.stringValue())
    return input
  }

}
