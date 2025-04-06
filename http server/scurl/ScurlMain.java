package scurl;

import java.util.*;

public class ScurlMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("scurl 실행 중... 명령을 입력하세요 (예: -v -H \"X-Custom-Header: NA\" http://example.com, 종료: exit)");

		while (true) {
			System.out.print("> ");
			String inputLine = scanner.nextLine().trim();

			// 입력을 큰따옴표를 유지하면서 분할
			String[] inputArgs = splitPreserveQuotes(inputLine);

			// "scurl" 제거 (필수 입력 아님)
			if (inputArgs.length > 0 && inputArgs[0].equalsIgnoreCase("scurl")) {
				inputArgs = Arrays.copyOfRange(inputArgs, 1, inputArgs.length);
			}

			// 옵션 분석
			OptionParser parser = new OptionParser(inputArgs);
			ScurlOption options = parser.parse();

			if (options == null) {
				continue;  // 오류 발생 시 다시 입력 받음
			}

			// HTTP 요청 수행
			HttpRequest request = new HttpRequest(options);
			request.send();
		}
	}

	/**
	 * 큰따옴표를 유지하면서 문자열을 올바르게 분할하는 메서드
	 */
	private static String[] splitPreserveQuotes(String input) {
		List<String> result = new ArrayList<>();
		StringBuilder current = new StringBuilder();
		boolean inQuotes = false;

		for (char c : input.toCharArray()) {
			if (c == '"') {
				inQuotes = !inQuotes; // 따옴표 상태 변경
				continue;
			}
			if (c == ' ' && !inQuotes) {
				if (current.length() > 0) {
					result.add(current.toString());
					current.setLength(0);
				}
			} else {
				current.append(c);
			}
		}

		if (current.length() > 0) {
			result.add(current.toString());
		}

		if (inQuotes) {
			System.err.println("오류: 닫히지 않은 따옴표가 있습니다.");
			return new String[0];
		}

		return result.toArray(new String[0]);
	}
}
