package net.rizov.cetus.runner

import net.rizov.cetus.Arg
import okio.Okio
import java.io.File

class Runner {

  private var token: Token = TokenFactory.BEGIN

  fun run(args: Array<Arg>) {
    runFile("test.ce")
  }

  private fun runFile(path: String) {
    val file = File(path)

    Okio.buffer(Okio.source(file)).use {
      token = it.readUtf8Line()
          ?.split(Regex("[ ]+"))
          ?.map(TokenFactory::create)
          ?.fold(token) { input, current -> current.execute(input) } ?: TokenFactory.END
    }
  }

}
