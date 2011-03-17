package templemore.sync.scalatypegoodness

/**
 * @author Chris Turner
 */
trait ContentFinder {

  implicit protected def finderToPartialFunction[FO <: ForeignObject, C](handler: LookupHandler[FO, C])
                                                       (implicit mFO: Manifest[FO], mC: Manifest[C]) =
    new PartialFunction[AnyRef, C] {
      def apply(foreignObject: AnyRef) =
        if ( isDefinedAt(foreignObject) ) handler(foreignObject.asInstanceOf[FO])
        else throw new IllegalStateException("We have managed to try and synchronise an unsupported class. This is a bug.")

      def isDefinedAt(foreignObject: AnyRef) =
        foreignObject.asInstanceOf[FO].contentType.compatibleWith(mC.erasure, mFO.erasure)
    }

  protected def finder[FO <: ForeignObject, C](lookup: FO => Option[C]) = new LookupHandler(lookup)

  protected class LookupHandler[FO <: ForeignObject, C](lookup: FO => Option[C]) {
    def apply(foreignObject: FO) = lookup (foreignObject).getOrElse(null).asInstanceOf[C]
  }
}
