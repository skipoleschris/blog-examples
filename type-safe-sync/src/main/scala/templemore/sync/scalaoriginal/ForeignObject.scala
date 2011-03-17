package templemore.sync.scalaoriginal

import java.util.UUID
import java.net.URL

/**
 * @author Chris Turner
 */
sealed trait ForeignObject {

  def localClass[C <: Content](): Class[_]
}

case class CMSForeignObject(typeName: String, id: String) extends ForeignObject {
  def localClass[C <: Content]() = typeName match {
    case "Story" => classOf[Story]
    case "Image" => classOf[Image]
    case "BinaryObject" => classOf[BinaryObject]
    case "RelatedLinks" => classOf[RelatedLinks]
    case _ => throw new IllegalStateException("Unknown type: " + typeName)
  }
}
object CMSForeignObject {
  def create(typeName: String) = new CMSForeignObject(typeName, UUID.randomUUID.toString)
}

case class ExternalForeignObject(typeName: String, url: URL) extends ForeignObject {
  def localClass[C <: Content]() = typeName match {
    case "Blog" => classOf[Blog]
    case _ => throw new IllegalStateException("Unknown type: " + typeName)
  }
}

case object BrokenForeignObject extends ForeignObject {
  def localClass[C <: Content]() = classOf[Story]
}
