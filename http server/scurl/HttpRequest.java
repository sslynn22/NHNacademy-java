package scurl;

import java.io.*;
import java.net.*;
import java.util.List;

public class HttpRequest {
	private ScurlOption options;

	public HttpRequest(ScurlOption options) {
		this.options = options;
	}

	public void send() {
		try {
			URL url = new URL(options.getUrl());
			String host = url.getHost();
			int port = (url.getPort() == -1) ? 80 : url.getPort();
			String path = url.getPath().isEmpty() ? "/" : url.getPath();

			InetAddress serverAddress = InetAddress.getByName(host);
			String serverIp = serverAddress.getHostAddress();

			if (options.isVerbose()) {
				System.out.println("* Trying " + serverIp + ":" + port + "...");
			}

			Socket socket = new Socket(serverIp, port);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			if (options.isVerbose()) {
				System.out.println("* Connected to " + host);
			}

			StringBuilder requestBuilder = new StringBuilder();
			requestBuilder.append(options.getMethod()).append(" ").append(path).append(" HTTP/1.1\r\n");
			requestBuilder.append("Host: ").append(host).append("\r\n");
			requestBuilder.append("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36\r\n");
			requestBuilder.append("Accept: */*\r\n");
			requestBuilder.append("Connection: keep-alive\r\n");

			// -H 옵션의 추가 헤더 처리
			List<String> customHeaders = options.getCustomHeader();
			for (String header : customHeaders) {
				requestBuilder.append(header).append("\r\n");
			}

			// POST, PUT 요청 시 데이터 추가
			if (options.getMethod().equals("POST") || options.getMethod().equals("PUT")) {
				String data = options.getData();
				if (data != null) {
					requestBuilder.append("Content-Length: ").append(data.length()).append("\r\n");
					requestBuilder.append("Content-Type: application/x-www-form-urlencoded\r\n");
					requestBuilder.append("\r\n").append(data);
				}
			} else {
				requestBuilder.append("\r\n");  // 요청 종료
			}

			String request = requestBuilder.toString();

			if (options.isVerbose()) {
				System.out.println("> " + request.replace("\r\n", "\n> "));
			}

			out.print(request);
			out.flush();

			String line;
			boolean isFirstLine = true;
			while ((line = in.readLine()) != null) {
				if (isFirstLine && options.isVerbose()) {
					System.out.println("< " + line);
					isFirstLine = false;
				} else if (options.isVerbose()) {
					System.out.println("< " + line);
				} else {
					System.out.println(line);
				}
			}
			socket.close();

		} catch (IOException e) {
			System.err.println("IO exception: " + e);
		}
	}
}

