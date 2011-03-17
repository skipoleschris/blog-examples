package changefinder;

import java.util.List;
import java.util.ArrayList;

public class ChangeFinder {

	private int changeAmount;
	
	public void calculateChange(final double cashGiven, final double amountRequired) {
		if ( cashGiven < amountRequired ) {
			throw new IllegalArgumentException();
		}
		changeAmount = (int)(cashGiven * 100) - (int)(amountRequired * 100);

		System.out.println(String.format("The required change is: £%.2f. Made up of: ", ((double)changeAmount) / 100));
		calculateChange();
	}	

	private void calculateChange() {
		int amount = changeAmount;
		for ( final Denomination denomination : Denomination.values() ) {
			int denominationValue = denomination.getValue();
			int quantity = amount / denominationValue;
			if ( quantity > 0 ) {
      		    amount -= quantity * denominationValue;
				System.out.println(String.format("%d x %s", quantity, denomination.toString()));
			}
		}
		assert(amount == 0); // This should always be true
	}

	public static void main(final String[] args) {
		if ( args.length != 2 ) {
			System.err.println("Please call the program with the amount of cash given and the amount required");
			System.exit(1);
		}
		
		final ChangeFinder changeFinder = new ChangeFinder();
		changeFinder.calculateChange(Double.valueOf(args[0]), Double.valueOf(args[1]));
	}
}
