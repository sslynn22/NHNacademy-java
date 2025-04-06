package basic_java.homework.poker;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RandomName implements Random{
	private String name;
	private String cardPattern;
	private int cardNum;

	public String getRandom() {
		return this.name;
	}

	public void setRandom() {
		List<String> fristName = Arrays.asList("김", "이", "박", "최", "오", "우", "아", "유", "정",
			"송", "장", "조", "강", "윤", "한", "권", "신", "안", "홍", "고", "문", "양");
		List<String> lastName = Arrays.asList("가", "강", "서", "연", "인", "아", "영", "안", "숙", "수", "영",
			"정", "희", "수", "민", "승", "동", "주", "형", "우", "주", "성", "민", "송", "은", "연", "선", "령",
			"현", "태", "진", "석", "유", "소", "용", "석", "지", "정", "복", "용", "나", "린", "경");
		Collections.shuffle(fristName);
		Collections.shuffle(lastName);
		this.name = fristName.get(0) + lastName.get(0) + lastName.get(1);
	}
}
