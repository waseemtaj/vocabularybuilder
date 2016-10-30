import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Routes" should {

    "send 404 on a bad request" in  {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) mustBe Some(NOT_FOUND)
    }

  }

  "VocabularySearchController" should {

    "render the index page" in {
      val searchTerm = "cricket"
      val search = route(app, FakeRequest(GET, s"/api/v1/search/$searchTerm")).get

      status(search) mustBe OK
      contentType(search) mustBe Some("application/json")
      contentAsString(search) must include (searchTerm)
    }

  }


}
