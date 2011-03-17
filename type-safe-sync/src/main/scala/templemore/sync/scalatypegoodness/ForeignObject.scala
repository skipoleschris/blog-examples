package templemore.sync.scalatypegoodness

import java.net.URL
import java.util.UUID

/**
 * @author Chris Turner
 */
sealed trait ForeignObject {

  def contentType[C <: Content, FO <: ForeignObject]: ContentType[C, FO]
}

case class CMSForeignObject(typeName: String, id: String) extends ForeignObject {

  def contentType[C <: Content, FO <: ForeignObject] = {
    val contentType = typeName match {
      case "Story" => new ContentType[Story, CMSForeignObject]
      case "Image" => new ContentType[Image, CMSForeignObject]
      case "BinaryObject" => new ContentType[BinaryObject, CMSForeignObject]
      case "RelatedLinks" => new ContentType[RelatedLinks, CMSForeignObject]
      case _ => throw new IllegalStateException("Unknown type: " + typeName)
    }
    contentType.asInstanceOf[ContentType[C, FO]]
  }
}
object CMSForeignObject {
  def create(typeName: String) = new CMSForeignObject(typeName, UUID.randomUUID.toString)
}

case class ExternalForeignObject(typeName: String, url: URL) extends ForeignObject {

  def contentType[C <: Content, FO <: ForeignObject] = {
    val contentType = typeName match {
      case "Blog" => new ContentType[Blog, ExternalForeignObject]
      case _ => throw new IllegalStateException("Unknown type: " + typeName)
    }
    contentType.asInstanceOf[ContentType[C, FO]]
  }
}

case class BrokenForeignObject(typeName: String) extends ForeignObject {
  def contentType[C <: Content, FO <: ForeignObject] =
    new ContentType[Story, BrokenForeignObject].asInstanceOf[ContentType[C, FO]]
}
