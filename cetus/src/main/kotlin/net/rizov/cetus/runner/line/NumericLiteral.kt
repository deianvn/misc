package net.rizov.cetus.runner.line

import net.rizov.cetus.runner.Token

abstract class NumericLiteral(private val value: String) : Token(value) {

  fun getByte() = value.toByte()

  fun getShort() = value.toShort()

  fun getInt() = value.toInt()

  fun getLong() = value.toLong()

  fun getFloat() = value.toFloat()

  fun getDouble() = value.toDouble()
}
