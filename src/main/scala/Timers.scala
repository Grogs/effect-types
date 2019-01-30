import java.util.concurrent.TimeUnit._
import java.util.concurrent.{Executors, ScheduledExecutorService, ThreadFactory, TimeUnit}

import cats.effect.{Clock, IO, Timer}

import scala.concurrent.duration.FiniteDuration

object Timers {

  implicit val catsTimer = new Timer[cats.effect.IO] {

    val ec = scala.concurrent.ExecutionContext.global

    val sc: ScheduledExecutorService =
      Executors.newScheduledThreadPool(2, new ThreadFactory {
        def newThread(r: Runnable): Thread = {
          val th = new Thread(r)
          th.setName(s"cats-effect-scheduler-${th.getId}")
          th.setDaemon(true)
          th
        }
      })

    override val clock: Clock[IO] =
      new Clock[IO] {
        override def realTime(unit: TimeUnit): IO[Long] =
          IO(unit.convert(System.currentTimeMillis(), MILLISECONDS))

        override def monotonic(unit: TimeUnit): IO[Long] =
          IO(unit.convert(System.nanoTime(), NANOSECONDS))
      }

    override def sleep(timespan: FiniteDuration): IO[Unit] =
      IO.cancelable { cb =>
        val tick = new Runnable {
          def run() = ec.execute(new Runnable {
            def run() = cb(Right(()))
          })
        }
        val f = sc.schedule(tick, timespan.length, timespan.unit)
        IO(f.cancel(false))
      }
  }


}
