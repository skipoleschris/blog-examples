package templemore.sync.scalaoriginal

/**
 * @author Chris Turner
 */
class SynchronisationService(repository: ContentRepository) {

  def sync(foreignObject: ForeignObject): Option[Content] = findById(foreignObject.localClass, foreignObject)

  private def findById[T <: Content](clazz: Class[_], foreignObject: ForeignObject) = {
    if ( clazz == classOf[Story] )
      repository.findStoryById(foreignObject.asInstanceOf[CMSForeignObject].id).asInstanceOf[Option[T]]
    else if ( clazz == classOf[Image] )
      repository.findImageById(foreignObject.asInstanceOf[CMSForeignObject].id).asInstanceOf[Option[T]]
    else if ( clazz == classOf[BinaryObject] )
      repository.findBinaryObjectById(foreignObject.asInstanceOf[CMSForeignObject].id).asInstanceOf[Option[T]]
    else throw new IllegalStateException("We have managed to try and synchronise an unsupported class. This is a bug.");
  }
}