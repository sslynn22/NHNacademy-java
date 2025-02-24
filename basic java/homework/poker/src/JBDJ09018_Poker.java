import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class JBDJ09018_Poker {


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("포커 게임에 참여할 인원을 입력해 주세요: ");
		int playerCount = sc.nextInt();
		System.out.print("플레이어의 이름을 입력해 주세요: ");
		String userName = sc.next();

		PlayCard game = new PlayCard(playerCount, userName);
		List<Player> players = game.getPlayers();

		game.showPlayerRank();

		Player winner = players.stream()
			.filter(p -> p.getRank() != null)
			.min(Comparator.comparing((Player p) -> p.getRank().ordinal()))
			.orElse(null);

		Player finalWinner = winner;
		List<Player> topPlayers = players.stream()
			.filter(p -> p.getRank() == finalWinner.getRank())
			.collect(Collectors.toList());

		if (topPlayers.size() > 1) {
			winner = topPlayers.stream()
				.max(Comparator.comparing((Player p) ->
						p.getCard().stream()
							.mapToInt(card -> card.number)
							.max()
							.orElse(0))
					.thenComparing(p ->
						p.getCard().stream()
							.max(Comparator.comparingInt(card -> card.number))
							.map(card -> card.pattern)
							.orElse(5)
					)
				)
				.orElse(null);
		}

		System.out.println("╔═══━━━─────   •   ─────━━━═══╗");
		System.out.println("        우승자는 "+ winner.getName() + "입니다");
		System.out.print("╚═══━━━─────   •   ─────━━━═══╝");
	}
}