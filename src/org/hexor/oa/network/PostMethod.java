package org.hexor.oa.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class PostMethod {
	private static final String TAG = "PostMethod";

	private ByteArrayOutputStream mBuffer;
	private String mContentType;

	public PostMethod(String contentType) {
		mBuffer = new ByteArrayOutputStream();
		mContentType = contentType;
	}

	protected void write(byte[] buffer) {
		try {
			mBuffer.write(buffer);
		} catch (IOException e) {
			Log.e(TAG, "PostMethod#write", e);
		}
	}

	public void reset() {
		mBuffer.reset();
	}

	public byte[] request(URL url) {
		HttpURLConnection conn = null;
		try {			
			conn = (HttpURLConnection) url.openConnection();
			// 30s timeout for bad mobile network throughput
			conn.setConnectTimeout(30000);
			conn.setRequestProperty("Content-Type", mContentType);
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setFixedLengthStreamingMode(mBuffer.size());
			mBuffer.writeTo(conn.getOutputStream());
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				byte[] buf = new byte[512];
				int len = -1;
				while ((len = conn.getInputStream().read(buf)) != -1) {
					os.write(buf, 0, len);
				}
				return os.toByteArray();
			} else {
				Log.w(TAG, "Server Response Code: " + conn.getResponseCode());
				return null;
			}
		} catch (IOException e) {
			Log.e(TAG, url.toString(), e);
		} finally {
			conn.disconnect();
			reset();
		}
		return null;
	}

	public String requestAsString(URL url) {
		return byteArrayToString(request(url));
	}

	public String dump() {
		String body = byteArrayToString(mBuffer.toByteArray());
		reset();
		return body;
	}
	
	private String byteArrayToString(byte[] buffer) {
		if (buffer == null) {
			return null;
		}
		return new String(buffer);
	}
}
