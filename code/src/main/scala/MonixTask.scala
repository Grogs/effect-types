import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.Future

object MonixTask extends Example {

  //given def upload(doc: String): Future[Unit]

  def uploadAll(input: List[String], concurrency: Int): Future[List[Unit]] = {
    Future.traverse(input)(upload)
  }

  val docs = (1 to 10).toList.map(_.toString)

  await(uploadAll(docs, 3))

}
