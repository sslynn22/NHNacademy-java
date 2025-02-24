package basic_java.homework.poker;

import java.util.ArrayList;
import java.util.List;

public class PlayCard {
	private final List<Player> players;
	private final RandomCard randomCard;

	public PlayCard(int playerCount, String userName) {
		if (playerCount < 2 || playerCount > 10 ) {
			throw new IllegalArgumentException("[ERROR] 게임은 2~10명이 참여 가능합니다.");
		}

		players = new ArrayList<>();
		randomCard = new RandomCard();
		randomCard.setRandom();

		RandomName randomName = new RandomName();
		players.add(new Player(userName, dealCards()));
		for (int i = 1; i < playerCount; i++) {
			randomName.setRandom();
			String playerName = randomName.getRandom();
			players.add(new Player(playerName, dealCards()));
		}
	}

	private List<Card> dealCards() {
		List<Card> card = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			card.add(randomCard.deck.remove(0));
		}
		return card;
	}

	public void showPlayersCards() {
		for (Player player : players) {
			System.out.println(player);
		}
	}

	public void showPlayerRank() {
		for (Player player : players) {
			Rank rank = PlayPoker.playPoker(player.getCard());
			player.setRank(rank);
			System.out.println(player.getName() + player.getCard() + "의 순위: " + player.getRank());
		}
	}

	public List<Player> getPlayers() {
		return players;
	}
}