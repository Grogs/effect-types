import cats.effect.IO

import scala.concurrent.Await
import scala.concurrent.duration.Duration.Inf


object CatsEffectIoExample extends App {

  def upload(doc: String): IO[Unit] = IO {
    Thread.sleep(500)
    println(s"Uploaded $doc")
  }

  Await.result({

    for {
      a <- upload("a")
      b <- upload("b")
    } yield ()

  }.unsafeToFuture(), Inf)

}