
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/nelly/Documents/Workspaces/challenges/simple-chat/chat/conf/routes
// @DATE:Fri Nov 04 16:43:07 EDT 2016


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
