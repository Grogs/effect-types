import $ivy.`io.monix::monix:3.0.0-RC2`

import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.{global => ec}
import monix.execution.Scheduler.Implicits.{global=>scheduler}
import monix.eval.Task
import scala.concurrent.duration._

def upload(doc: String): Future[Unit] =
  Task(println(s"Uploaded $doc")).delayExecution(250.millis).runToFuture


//given def upload(doc: String): Future[Unit]
import monix.eval.Task

def uploadMonix(doc: String) = Task.fromFuture(upload(doc))

def uploadAll(input: List[String], concurrency: Int): Task[List[Unit]] = {
  Task.traverse(input)(uploadMonix)
}

val docs = (1 to 10).toList.map(_.toString)

println(1)
Await.result(uploadAll(docs, 3).runToFuture, Duration.Inf)
println(2)
