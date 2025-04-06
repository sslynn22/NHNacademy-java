package scurl;

import java.util.*;

public class OptionParser {
	private String[] args;

	public OptionParser(String[] args) {
		this.args = args;
	}

	public ScurlOption parse() {
		ScurlOption options = new ScurlOption();
		String detectedUrl = null;

		for (int i = 0; i < args.length; i++) {
			String arg = args[i];

			switch (arg) {
				case "-v":
					options.setVerbose(true);
					break;
				case "-X":
					if (i + 1 < args.length) {
						String method = args[++i].toUpperCase();
						if (!method.matches("GET|HEAD|POST|PUT|DELETE")) {
							System.err.println("Invalid option: " + arg);
							return null;
						}
						options.setMethod(method);
					} else {
						System.err.println("Invalid input: " + arg + ", must enter HTTP method");
						return null;
					}
					break;
				case "-d":
					if (i + 1 < args.length) {
						options.setData(args[++i]);
					} else {
						System.err.println("Invalid input: " + arg + ", must enter data");
						return null;
					}
					break;
				case "-H":
					if (i + 1 < args.length) {
						options.addCustomHeader(args[++i]);
					} else {
						System.err.println("Invalid input: " + arg + ", must enter header value");
						return null;
					}
					break;
				default:
					if (detectedUrl == null && arg.startsWith("http")) {
						detectedUrl = arg;
					} else {
						System.err.println("Invalid input: " + Arrays.toString(args) + ", must enter URL");
						return null;
					}
					break;
			}
		}

		if (detectedUrl == null) {
			System.err.println("오류: URL이 필요합니다.");
			return null;
		}

		options.setUrl(detectedUrl);
		return options;
	}
}

