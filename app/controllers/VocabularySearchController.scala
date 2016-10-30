package controllers

import javax.inject.{Singleton, Inject}

import akka.actor.ActorSystem
import play.api.libs.json.{JsArray, Json, JsObject}
import play.api.mvc.{Action, Controller}


import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.concurrent.duration._


@Singleton
class VocabularySearchController @Inject() (actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends Controller {

  def search(word: String) = Action.async {
    Future {
      Ok(resultsAsJson(Seq(s"$word means something awesome - go find it in google")))
    }
  }

  private def resultsAsJson(results: Seq[String]) = {
    JsObject(Seq(
      "results" -> JsArray(
        results.zipWithIndex.map { case(msg, counter) => messageAsJson(msg, counter) }
      )
    ))

  }

  private def messageAsJson(message: String, index: Int) = {
    JsObject(Seq("id" -> Json.toJson(index), "meaning" ->Json.toJson(message)))
  }
}
