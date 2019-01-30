import Timers._
import cats.effect._
import cats.effect.syntax.effect._
import cats.syntax.flatMap._
import cats.syntax.functor._
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.Await
import scala.concurrent.duration.Duration.Inf
import scala.concurrent.duration._
import scala.language.higherKinds
import scalaz.zio.interop.catz._
import scalaz.zio.interop.monixio._



object CatsEffectTypeclassExample extends App {
  implicit val clock = scalaz.zio.Clock.Live

  def upload[F[_] : Timer : Sync](doc: String): F[Unit] = {
    for {
      _ <- Timer[F].sleep(500.millis)
      _ <- Sync[F].delay(println(s"Uploaded $doc"))
    } yield ()
  }


  //Cats
  val catsIoUpload = for {
    a <- upload[IO]("a with Cats")
    b <- upload("b with Cats")
  } yield ()


  //Monix
  val monixTaskUpload = for {
    a <- upload[Task]("a with Monix")
    b <- upload[Task]("b with Monix")
  } yield ()

  //ZIO
  val zioUpload = for {
    a <- upload[scalaz.zio.IO[Throwable, ?]]("a with ZIO")
    b <- upload[scalaz.zio.IO[Throwable, ?]]("b with ZIO")
  } yield ()

  Await.result(catsIoUpload.unsafeToFuture(), Inf)
  Await.result(monixTaskUpload.runToFuture, Inf)
  Await.result(Effect[scalaz.zio.IO[Throwable, ?]].toIO(zioUpload).unsafeToFuture(), Inf)





  //Interop
  //Everything to ZIO IO
  LiftIO[scalaz.zio.IO[Throwable, ?]].liftIO(catsIoUpload)
  scalaz.zio.IO.fromTask(monixTaskUpload)

  //Everything to Cats Effect IO
  Effect[scalaz.zio.IO[Throwable, ?]].toIO(zioUpload)
  monixTaskUpload.toIO

  //Everything to Monix Task
  Task.fromIO(catsIoUpload)
  Task.fromEffect[scalaz.zio.IO[Throwable, ?], Unit](zioUpload)

}