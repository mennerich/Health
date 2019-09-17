package controllers

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton
class UserController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {


  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.user.index())
  }

}