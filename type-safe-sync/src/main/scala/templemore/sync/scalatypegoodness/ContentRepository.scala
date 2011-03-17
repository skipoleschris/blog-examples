package templemore.sync.scalatypegoodness

/**
 * @author Chris Turner
 */
class ContentRepository {

  def findStoryById(id: String) = Some(new Story(id));
  def findImageById(id: String) = Some(new Image(id));
  def findBinaryObjectById(id: String) = Some(new BinaryObject(id));
}
