import monix.eval.Task
import monix.execution.Scheduler

import scala.concurrent.duration._

object MonixTaskHelpfulMethods extends Example {

  val task = Task(???)

  task.memoize
  task.memoizeOnSuccess

  task.onErrorRestart(3).memoizeOnSuccess

  val ioScheduler = Scheduler.io()
  task.executeOn(ioScheduler)

}
