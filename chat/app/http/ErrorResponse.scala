package http


import com.fasterxml.jackson.annotation.JsonProperty
import play.api.libs.json.{JsArray, JsString, Json, Writes}


case class ErrorResponse( @JsonProperty val errors: java.util.List[String]) {
  def toJsonString: String = {
    Json.toJson(this).toString()
  }

  implicit val errorResponseWrites = new Writes[ErrorResponse] {
    def writes(errorResponse: ErrorResponse) = {
      val e = errorResponse.errors.toArray()
      Json.obj("errors" -> JsArray(e.map( a => JsString(a.asInstanceOf[String]) )))
    }
  }
}
