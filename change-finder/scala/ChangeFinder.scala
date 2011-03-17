package changefinder;

class ChangeFinder {
	
  def calculateChange(cashGiven: Double, amountRequired: Double): Pair[Double, List[ChangeItem]] = {
    require(cashGiven >= amountRequired)
    val changeAmount = convertToPence(cashGiven) - convertToPence(amountRequired)
    (convertToPounds(changeAmount), workOutChangeFor(changeAmount))
  }

  private def workOutChangeFor(amount: Int) =
  	Denomination.denominations.foldLeft(ChangeTotal(amount, List())) { (total, denomination) =>
  		if ( total.amountRemaining < denomination.value ) total
  		else updateTotalFromDenomination(total, denomination) 
  	}.change.reverse

  private def updateTotalFromDenomination(total: ChangeTotal, denomination: Denomination) =
  	total.reduceBy(total.amountRemaining / denomination.value, denomination)

  private def convertToPence(amount: Double) = (amount * 100).toInt
  private def convertToPounds(amount: Int) = amount.toDouble / 100.0

  private case class ChangeTotal(val amountRemaining: Int, val change: List[ChangeItem] = List[ChangeItem]()) {
  	def reduceBy(quantity: Int, denomination: Denomination) = 
  	  new ChangeTotal(amountRemaining - (quantity * denomination.value),
  	                  ChangeItem(denomination, quantity) :: change)
  }
}

case class ChangeItem(denomination: Denomination, quantity: Int) {
	override def toString = "%d x %s".format(quantity, denomination.toString)
}

object ChangeFinder {
  def main(args: Array[String]) = {
    if ( args.length != 2 ) {
  	  System.err.println("Please call the program with the amount of cash given and the amount required");
	    System.exit(1);
    }

    val changeFinder = new ChangeFinder
    val change = changeFinder.calculateChange(args(0).toDouble, args(1).toDouble)
 
    println("The required change is: \u00A3%.2f. Made up of: ".format(change._1));
    change._2.foreach(item => println(item.toString))
  }
}