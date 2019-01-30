import java.util.concurrent.TimeUnit

import scalaz.zio.duration.Duration
import scalaz.zio.{App, IO, duration}


object ScalazZioExample extends App {

  def upload(doc: String): IO[Nothing, Unit] = {
    for {
     _ <- IO.sleep(duration.Duration(500, TimeUnit.MILLISECONDS))
      _ <- IO.blocking(println(s"Uploaded $doc"))
    } yield ()
  }

  def run(args: List[String]) = {
    for {
      a <- upload("a")
      b <- upload("b")
    } yield ExitStatus.ExitWhenDone(0, Duration.Infinity)
  }
}