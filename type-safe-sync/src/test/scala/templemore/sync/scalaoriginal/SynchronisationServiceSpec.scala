package templemore.sync.scalaoriginal

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import java.net.URL

/**
 * @author Chris Turner
 */
class SynchronisationServiceSpec extends FlatSpec with ShouldMatchers {

  private val repository = new ContentRepository
  private val syncService = new SynchronisationService(repository)

  "A synchronisation service" should "find a Story matching the supplied foreign object" in {
    val foreignObject = CMSForeignObject.create("Story")
    val story = syncService.sync(foreignObject).asInstanceOf[Option[Story]]

    story should not be (None)
    story.get.id should be (foreignObject.id)
  }

  it should "find an Image matching the supplied foreign object" in {
    val foreignObject = CMSForeignObject.create("Image")
    val image = syncService.sync(foreignObject).asInstanceOf[Option[Image]]

    image should not be (None)
    image.get.id should be (foreignObject.id)
  }

  it should "find a BinaryObject matching the supplied foreign object" in {
    val foreignObject = CMSForeignObject.create("BinaryObject")
    val binaryObject = syncService.sync(foreignObject).asInstanceOf[Option[BinaryObject]]

    binaryObject should not be (None)
    binaryObject.get.id should be (foreignObject.id)
  }

  it should "fail if an unsupported type of foreign object is used" in {
    val foreignObject = new ExternalForeignObject("Blog", new URL("http://www.templemore.co.uk/blog"))

    intercept[IllegalStateException] {
      val blog = syncService.sync(foreignObject)
    }
  }

  it should "fail if an unsupported type of content is used" in {
    val foreignObject = CMSForeignObject.create("RelatedLinks")

    intercept[IllegalStateException] {
      val blog = syncService.sync(foreignObject)
    }
  }

  it should "fail if a broken foreign object is used" in {
    val foreignObject = BrokenForeignObject

    // Ideally we would prefer if this threw an IllegalStateException, but it doesn't
    intercept[ClassCastException] {
      val blog = syncService.sync(foreignObject)
    }
  }
}