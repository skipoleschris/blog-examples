package templemore.sync.scalaoriginal

/**
 * @author Chris Turner
 */
sealed trait Content

case class Story(id: String) extends Content
case class Image(id: String) extends Content
case class BinaryObject(id: String) extends Content
case class RelatedLinks(id: String) extends Content
case class Blog(title: String, author: String) extends Content
