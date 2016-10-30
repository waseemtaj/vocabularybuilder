package controllers

import javax.inject.{Singleton, Inject}

import akka.actor.ActorSystem
import domain.{WordSearch, DictionaryAPI}
import play.api.libs.json.{JsArray, Json, JsObject}
import play.api.mvc.{Action, Controller}


import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.concurrent.duration._
import scala.util.{Success, Failure}


@Singleton
class VocabularySearchController @Inject() (actorSystem: ActorSystem, dictApi: DictionaryAPI)(implicit exec: ExecutionContext) extends Controller {

  def search(word: String) = Action.async {
//    Future {
//      Ok(resultsAsJson(Seq(s"$word means something awesome - go find it in google")))
//    }
    dictApi.search(word).map { r =>
      Ok(resultsAsJson(r))
//      case Success(r) => Ok(resultsAsJson(r))
//      case Failure(e) => Ok(Json.toJson("error while processing request: " + e.toString))
    }
//    Future{Ok("hello") }
  }



  private def resultsAsJson(results: Seq[WordSearch]) = {
    JsObject(Seq(
      "results" -> JsArray(
        results.zipWithIndex.map { case(msg, counter) => messageAsJson(msg, counter) }
      )
    ))

  }

  private def messageAsJson(message: WordSearch, index: Int) = {
    JsObject(Seq("id" -> Json.toJson(index), "meaning" ->Json.toJson(message.meaning)))
  }
}
