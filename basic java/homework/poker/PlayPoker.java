package basic_java.homework.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PlayPoker {
	public static Rank playPoker(List<Card> cardHand) {
		List<Integer> numbers = new ArrayList<>();
		List<Integer> patterns = new ArrayList<>();

		for (Card card : cardHand) {
			numbers.add(card.number);
			patterns.add(card.pattern);
		}

		Collections.sort(numbers);

		boolean isFlush = isSamePattern(patterns);
		boolean isStraight = isConsecutive(numbers);
		Map<Integer, Integer> isPair = getPair(numbers);

		if (isStraight && isFlush) return Rank.STRAIGHT_FLUSH;
		else if(isPair.containsValue(4)) return Rank.FOUR_OF_A_KIND;
		else if(isPair.containsValue(3) && isPair.containsKey(2)) return Rank.THREE_OF_A_KIND;
		else if(isFlush) return Rank.FLUSH;
		else if(isStraight) return Rank.STRAIGHT;
		else if(isPair.containsValue(3) && isPair.containsKey(0)) return Rank.THREE_OF_A_KIND;
		else if(isPair.containsValue(2) && isPair.containsKey(2)) return Rank.TWO_PAIR;
		else if(isPair.containsValue(1)) return Rank.ONE_PAIR;
		else return Rank.HIGH_CARD;
	}

	public static boolean isSamePattern(List<Integer> patterns) {
		Set<Integer> set = new HashSet<Integer>(patterns);
		if (set.size() == 1) return true;
		else return false;
	}

	private static boolean isConsecutive(List<Integer> patterns) {
		for (int i = 0; i < 4 ; i++) {
			if (patterns.get(i) + 1 != patterns.get(i+1)) return false;
		}
		return true;
	}

	private static Map<Integer, Integer> getPair(List<Integer> patterns) {
		Map<Integer, Integer> count = new HashMap<>();
		Set<Integer> setNumber = new HashSet<Integer>(patterns);

		int[] arr = new int[14];
		if (setNumber.size() == 4) {	// 동일 숫자 2장 + 다른 3장(원페어)
			count.put(0, 1);
		}
		if (setNumber.size() == 3) {    // 트리플 or 투페어
			for (int num : patterns) {
				arr[num]++;
			}
			int pairCount = 0;
			int tripleCount = 0;

			for (int value : arr) {
				if (value == 2)
					pairCount++;
				if (value == 3)
					tripleCount++;
			}

			if (pairCount == 2)		// 투페어
				count.put(2, 2);
			else if (pairCount == 1)	// 페어
				count.put(0, 2);
			else if (tripleCount == 1)		// 트리플
				count.put(0, 3);
			else if (tripleCount == 1 && pairCount == 1)	// 풀 하우스
				count.put(2, 3);
		}
		if (setNumber.size() == 2) {	// 포 카드
			count.put(0, 4);
		}

		return count;
	}
}
