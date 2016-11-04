package http


import java.util.UUID
import play.mvc.Http
import play.mvc.Http.Session
import scala.util.{Success, Try}


object Secured {
   val cookieName = "_user_"
   
   def getCookie(session: Session, request: Http.Request) :Option[SimpleCookie] = {
      cookieIsValid(session, request) match {
         case false => None
         case true => val cS = session.get(s"${cookieName}").split("|")
            Some(SimpleCookie(UUID.fromString(cS(0)), cS(1)))
      }
   }

   def setCookie(session: Session, cookie: SimpleCookie) :Unit = session.put(cookieName, cookie.toString())

   
   def cookieIsValid(session: Session, request: Http.Request) :Boolean = {
      session.get(s"${cookieName}") match {
         case cookieString: String => {
            val pieces = cookieString.split("|")
            if(pieces.length != 2) false else {
               if(pieces(1) != request.remoteAddress()) false else {
                  Try(UUID.fromString(pieces(0))) match {
                     case Success(userId) => true
                     case _ => false
                  }
               }
            }
         }
         case _ => false
      }
   }
}

case class SimpleCookie(userId: UUID, userAgent: String) {
   override  def toString() : String = s"${userId.toString}|${userAgent}"
}