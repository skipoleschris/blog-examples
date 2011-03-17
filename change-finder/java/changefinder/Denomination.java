package changefinder;

public enum Denomination {
	
	fiftyPound(5000, "£50"),
	twentyPound(2000, "£20"),
	tenPound(1000, "£10"),
	fivePound(500, "£5"),
	twoPound(200, "£2"),
	onePound(100, "£1"),
	fiftyPence(50, "50p"),
	twentyPence(20, "20p"),
	tenPence(10, "10p"),
	fivePence(5, "5p"),
	twoPence(2, "2p"),
	onePence(1, "1p");

	private final int value;
	private final String display;

	Denomination(final int value, final String display) {
		this.value = value;
		this.display = display;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return display;
	}
}