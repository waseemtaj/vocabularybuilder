import javax.inject._
import play.api._
import play.api.http.HttpFilters
import play.api.mvc._

import filters.ExampleFilter

@Singleton
class Filters @Inject() (env: Environment) extends HttpFilters {

  override val filters =  Seq.empty
}
