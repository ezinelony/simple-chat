package server


class ChatServer() {
  
  /*
  implicit val actorSystem = ActorSystem("chat-akka-system")
  implicit val flowMaterializer = ActorMaterializer()

  println(s" _____ ")
  /*
      save message, then broadcast message
   */
  val echoService: Flow[Message, Message, _] = Flow[Message].map {
    case TextMessage.Strict(txt) => TextMessage("ECHO: " + txt)
    case _ => TextMessage("Message type unsupported")
  }
  
  val interface = "localhost" //env
  val port = 8080  //env

  import akka.http.scaladsl.server.Directives._

  val route = get {
    pathEndOrSingleSlash {
      complete("Welcome to websocket server")
    }
  } ~
    path("chats") {
      get {
        handleWebSocketMessages(echoService)
      }
    }

  val binding = Http().bindAndHandle(route, interface, port)
  println(s"Server is now online at http://$interface:$port\nPress RETURN to stop...")
  //StdIn.readLine()

  import actorSystem.dispatcher

  binding.flatMap(_.unbind()).onComplete(_ => actorSystem.terminate())
  println("Server is down...")
  */
  
  
}
