package net.rizov.cetus

class Input(private val args: Array<String>) {

  fun validate(args: Array<String>) = true

  fun mode() = Mode.RUNNER

  fun args() = arrayOf<Arg>()

  enum class Mode { RUNNER, COMPILER }

}
