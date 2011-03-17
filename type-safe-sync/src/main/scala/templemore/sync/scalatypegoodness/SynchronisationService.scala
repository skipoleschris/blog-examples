package templemore.sync.scalatypegoodness

/**
 * @author Chris Turner
 */
class SynchronisationService(repository: ContentRepository) extends ContentFinder {

  def sync(foreignObject: ForeignObject): Option[Content] = findById(foreignObject)

  private def findById[C <: Content, FO <: ForeignObject](foreignObject: FO) =
    Option(finder(findStory) orElse finder(findImage) orElse finder(findBinaryObject) apply foreignObject)

  private def findStory(foreignObject: CMSForeignObject) = repository findStoryById (foreignObject id)
  private def findImage(foreignObject: CMSForeignObject) = repository findImageById (foreignObject id)
  private def findBinaryObject(foreignObject: CMSForeignObject) = repository findBinaryObjectById (foreignObject id)

}
