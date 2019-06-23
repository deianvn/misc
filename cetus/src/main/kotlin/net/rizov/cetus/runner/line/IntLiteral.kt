package net.rizov.cetus.runner.line

import net.rizov.cetus.runner.Token
import net.rizov.cetus.runner.TokenFactory

class IntLiteral(private val value: Int) : NumericLiteral(value.toString()) {

  override fun execute(input: Token) = when (input) {
    TokenFactory.BEGIN -> this
    is NumericLiteral -> IntLiteral(value + input.getInt())
    else -> throw IllegalStateException()
  }
}
