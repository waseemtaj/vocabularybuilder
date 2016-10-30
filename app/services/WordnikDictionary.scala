package services

import javax.inject.{Singleton, Inject}
import play.api.libs.json.Json

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration._

import domain.{WordSearch, DictionaryAPI}
import play.api.Application
import play.api.libs.ws.{WS, WSClient}

class WordnikDictionary @Inject() (client: WSClient) (implicit exec: ExecutionContext) extends DictionaryAPI {
  override def search(query: String): Future[Seq[WordSearch]] =  {
    //http://api.wordnik.com:80/v4/word.json/excite/topExample?useCanonical=false&api_key=a2a73e7b926c924fad7001ca3111acd55af2ffabf50eb4ae5

    val request = client.url(s"http://api.wordnik.com:80/v4/word.json/$query/definitions")
         .withHeaders("Accept" -> "application/json")
        .withRequestTimeout(10000.millis)
        .withQueryString(
          "api_key" -> "XXX",
          "limit" -> "200",
          "includeRelated" -> "true",
          "sourceDictionaries" -> "all"
        )

    request.get().map { r =>
      println(s"recieived json: ${Json.stringify(r.json)}")
      (r.json \\ "text").map(_.as[String]).map(WordSearch(query, _))
    }
  }
}

//class WordnikClient(url: URL, username: String, password: String) {
//
//}
//
//@Singleton
//class WordnikClient @Inject() (client: WSClient) {
//  def apply(url: URL) (implicit app: Application)= {
//    val request = client.url(url.toString)
//    request.withAuth(username, password, )
//
//  }
//}