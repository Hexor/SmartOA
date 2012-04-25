package org.hexor.oa.network;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import android.util.Log;

public class MultipartFormBuilder extends PostMethod {
	private static final String TAG = "MultipartFormBuilder";
	private static final String BOUNDARY = "--a83640d3b7ab";
	private static final String CRLF = "\r\n";
	private static final String SECTION_PREFIX = "Content-Disposition: form-data; name=\"";

	private static final String PREFIX = "--" + BOUNDARY + CRLF + SECTION_PREFIX;
	private static final String SUFFIX = CRLF + "--"+ BOUNDARY + "--" + CRLF + CRLF;

	public MultipartFormBuilder() {
		super("multipart/form-data; boundary=" + BOUNDARY);
	}

	public MultipartFormBuilder appendField(String name, String content) {
		write(new StringBuilder(PREFIX).append(name).append("\"").append(CRLF)
				.append(CRLF).append(content).append(CRLF).toString()
				.getBytes());
		return this;
	}

	public MultipartFormBuilder appendField(String name, File file,
			String mimetype) {
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] buf = new byte[fis.available()];
			fis.read(buf);
			return appendField(name, file.getAbsolutePath(), buf, mimetype);
		} catch (FileNotFoundException e) {
			Log.e(TAG, file.getAbsolutePath(), e);
		} catch (IOException e) {
			Log.e(TAG, file.getAbsolutePath(), e);
		}
		return this;
	}

	public MultipartFormBuilder appendField(String name, String fileName,
			byte[] file, String mimetype) {
		write(new StringBuilder(CRLF).toString().getBytes());		
		write(new StringBuilder(PREFIX).append(name).append("\"; filename=\"")
				.append(fileName).append("\"").append(CRLF)
				.append("Content-Type: ").append(mimetype).append(CRLF)
				.append(CRLF).toString().getBytes());
		write(file);
		write(CRLF.getBytes());
		return this;
	}

	@Override
	public String dump() {
		write(SUFFIX.getBytes());
		return super.dump();
	}

	@Override
	public byte[] request(URL url) {
		write(SUFFIX.getBytes());
		return super.request(url);
	}
}
