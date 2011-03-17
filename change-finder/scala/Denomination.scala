package changefinder;

object Denomination {
	
	lazy val denominations = FiftyPound :: TwentyPound :: TenPound :: FiftyPound :: TwoPound :: OnePound ::
	                         FiftyPence :: TwentyPence :: TenPence :: FivePence :: TwoPence :: OnePence :: Nil
}

sealed trait Denomination {
  def value: Int	
}

abstract class BaseDenomination(val value: Int, val display: String) extends Denomination {
  override def toString = display
}
case object FiftyPound extends BaseDenomination(5000, "\u00A350")
case object TwentyPound extends BaseDenomination(2000, "\u00A320")
case object TenPound extends BaseDenomination(1000, "\u00A310")
case object FivePound extends BaseDenomination(500, "\u00A35")
case object TwoPound extends BaseDenomination(200, "\u00A32")
case object OnePound extends BaseDenomination(100, "\u00A31")
case object FiftyPence extends BaseDenomination(50, "50p")
case object TwentyPence extends BaseDenomination(20, "20p")
case object TenPence extends BaseDenomination(10, "10p")
case object FivePence extends BaseDenomination(5, "5p")
case object TwoPence extends BaseDenomination(2, "2p")
case object OnePence extends BaseDenomination(1, "1p")
