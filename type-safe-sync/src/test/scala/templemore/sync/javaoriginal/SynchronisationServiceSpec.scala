package templemore.sync.javaoriginal

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import java.net.URL

/**
 * @author Chris Turner
 */
class SynchronisationServiceSpec extends FlatSpec with ShouldMatchers {

  private val repository = new ContentRepository
  private val syncService = new SynchronisationService(repository)

  "A synchronisation service" should "find a Story matching the supplied foreign object" in {
    val foreignObject = new CMSForeignObject("Story")
    val story = syncService.sync(foreignObject).asInstanceOf[Story]

    story should not be (null)
    story.getId should be (foreignObject.getId)
  }

  it should "find an Image matching the supplied foreign object" in {
    val foreignObject = new CMSForeignObject("Image")
    val image = syncService.sync(foreignObject).asInstanceOf[Image]

    image should not be (null)
    image.getId should be (foreignObject.getId)
  }

  it should "find a BinaryObject matching the supplied foreign object" in {
    val foreignObject = new CMSForeignObject("BinaryObject")
    val binaryObject = syncService.sync(foreignObject).asInstanceOf[BinaryObject]

    binaryObject should not be (null)
    binaryObject.getId should be (foreignObject.getId)
  }

  it should "fail if an unsupported type of foreign object is used" in {
    val foreignObject = new ExternalForeignObject("Blog", new URL("http://blogs.com/myblog"))

    intercept[IllegalStateException] {
      val blog = syncService.sync(foreignObject)
    }
  }

  it should "fail if an unsupported type of content is used" in {
    val foreignObject = new CMSForeignObject("RelatedLinks")

    intercept[IllegalStateException] {
      val blog = syncService.sync(foreignObject)
    }
  }

  it should "fail if a broken foreign object is used" in {
    val foreignObject = new BrokenForeignObject()

    // Ideally we would prefer if this threw an IllegalStateException, but it doesn't
    intercept[ClassCastException] {
      val blog = syncService.sync(foreignObject)
    }
  }
}