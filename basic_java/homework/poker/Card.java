package basic_java.homework.poker;

public class Card {
	int pattern;
	int number;

	public Card(int pattern, int number) {
		this.pattern = pattern;
		this.number = number;
	}

	public String toString() {
		String[] suits = {"♠", "♦", "♥", "♣"};
		return suits[pattern - 1] + number;
	}
}
