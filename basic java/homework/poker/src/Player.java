import java.util.List;

public class Player {
	private final String name;
	private final List<Card> card;
	private Rank rank;

	public Player(String name, List<Card> card) {
		this.name = name;
		this.card = card;
		this.rank = Rank.HIGH_CARD;
	}

	public String getName() {
		return name;
	}

	public List<Card> getCard() {
		return card;
	};

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Rank getRank() {
		return rank;
	}

	@Override
	public String toString() {
		String output = name + ": ";
		for (int i = 0; i < 5 ; i++) {
			output += card.get(i).toString();
			if (i < 4) {
				output += ", ";
			}
		}
		return
			output;
	}
}
