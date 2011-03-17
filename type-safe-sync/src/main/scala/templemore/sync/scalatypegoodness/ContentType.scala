package templemore.sync.scalatypegoodness

/**
 * @author Chris Turner
 */
class ContentType[C <: Content, FO <: ForeignObject](implicit val mC: Manifest[C], val mFO: Manifest[FO]) {

  def compatibleWith(cClass: Class[_], foClass: Class[_]) = (cClass == mC.erasure) && (foClass == mFO.erasure)
}
