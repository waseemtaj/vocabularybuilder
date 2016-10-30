package domain

import scala.concurrent.Future

trait DictionaryAPI {
  def search(query: String) :Future[Seq[WordSearch]]
}
