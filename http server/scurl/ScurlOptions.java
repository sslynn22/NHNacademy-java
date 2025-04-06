package scurl;

import java.util.ArrayList;
import java.util.List;

public class ScurlOptions {
	private String method = "GET";  // 기본값 GET
	private String url;
	private String data = null;  // -d 옵션의 데이터
	private boolean verbose = false; // -v 옵션 여부
	private List<String> customHeaders = new ArrayList<>(); // -H 옵션 저장 리스트

	public String getMethod() { return method; }
	public void setMethod(String method) { this.method = method; }

	public String getUrl() { return url; }
	public void setUrl(String url) { this.url = url; }

	public String getData() { return data; }
	public void setData(String data) { this.data = data; }

	public boolean isVerbose() { return verbose; }
	public void setVerbose(boolean verbose) { this.verbose = verbose; }

	public List<String> getCustomHeaders() { return customHeaders; }
	public void addCustomHeader(String header) { customHeaders.add(header); }
}

