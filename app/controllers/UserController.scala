package controllers

import javax.inject._
import models._
import play.api._
import play.api.mvc._
import protocols.Protocol._
import scala.concurrent.{ ExecutionContext, Future }
import scala.concurrent.{ Await, ExecutionContext, Future }
import scala.concurrent.duration._


@Singleton
class UserController @Inject()(implicit ec: ExecutionContext, cc: ControllerComponents,  userRepo: UserRepo) extends AbstractController(cc) {


  def index() = Action.async { implicit request: Request[AnyContent] =>
    Await.result(userRepo.all, 2.seconds) match {
      case users: List[User] => Future(Ok("ok"))
      case default => Future(InternalServerError("Internal Server Error"))
    }
  }


}