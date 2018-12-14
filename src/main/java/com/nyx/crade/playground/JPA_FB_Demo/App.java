package com.nyx.crade.playground.JPA_FB_Demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyx.crade.playground.JPA_FB_Demo.entity.FbPost;
import com.nyx.crade.playground.JPA_FB_Demo.json.FbApiResult;

public class App {
	public static PostManager manager = new PostManager();

	public static void main(String[] args) throws IOException {
		manager.setup();

		// visit https://developers.facebook.com/tools/explorer/ to generate an access token
		String accessToken = "ACCESS_TOKEN";
		String graphUrl = "https://graph.facebook.com/v3.2/me?fields=feed.limit(10)&access_token=";

		URL url = new URL(graphUrl + accessToken);
		byte[] data = readUrl(url);
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		FbApiResult result = mapper.readValue(data, FbApiResult.class);

		result.getFeed().getData().stream().forEach(App::storePost);
	}

	private static void storePost(FbPost post) {
		manager.create(post);
	}

	private static byte[] readUrl(URL url) throws IOException {
		URLConnection connection = url.openConnection();
		InputStream inputStream = connection.getInputStream();

		byte[] buffer = new byte[4096];
		int i;

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		while ((i = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, i);
		}
		outputStream.close();
		byte[] result = outputStream.toByteArray();
		return result;
	}
}
