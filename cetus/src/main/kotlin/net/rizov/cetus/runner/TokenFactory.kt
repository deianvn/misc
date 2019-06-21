package net.rizov.cetus.runner

import net.rizov.cetus.runner.block.BeginCommand
import net.rizov.cetus.runner.block.EndCommand
import net.rizov.cetus.runner.line.IntLiteral

object TokenFactory {

  val BEGIN = BeginCommand()
  val END = EndCommand()

  private val LITERAL_REGEX = Regex("\\d+")
  private val OPERATOR_REGEX = Regex("\\+(1)")

  fun create(value: String): Token = when {
    value.matches(LITERAL_REGEX) -> createLiteral(value)
    value.matches(OPERATOR_REGEX) -> createOperator(value)
    else -> error(value)
  }

  private fun createLiteral(value: String): Token {
    return IntLiteral(value.toInt())
  }

  private fun createOperator(value: String): Token {
    return IntLiteral(value.toInt())
  }

  private fun error(value: String): Token {
    throw IllegalStateException("$value")
  }
}
