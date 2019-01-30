import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration.Inf
import scala.concurrent.{Await, Future}

object FutureExample extends App {

  def upload(doc: String): Future[Unit] = Future {
    Thread.sleep(500)
    println(s"Uploaded $doc")
  }

  Await.result({

    for {
      a <- upload("a")
      b <- upload("b")
    } yield ()

  }, Inf)
}

object Iterations extends App {
  for (i<- 1 to 10) {
    FutureExample.main(Array.empty)
    println("------")
  }
}
