package net.rizov.cetus

import net.rizov.cetus.compiler.Compiler
import net.rizov.cetus.runner.Runner

fun main(args: Array<String>) {
  val input = Input(args)
  when (input.mode()) {
    Input.Mode.RUNNER -> Runner().run(input.args())
    Input.Mode.COMPILER -> Compiler().compile(input.args())
  }
}
