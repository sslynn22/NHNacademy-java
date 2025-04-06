package basic_java.homework.poker;

import java.util.ArrayList;
import java.util.Collections;

public class RandomCard implements Random {
	ArrayList<Card> deck = new ArrayList<Card>();

	public void setRandom() {
		for(int i = 1; i <= 4; i++){
			for(int j = 1; j <= 13; j++)
				deck.add(new Card(i,j));
		}
		Collections.shuffle(deck);
	}
}
