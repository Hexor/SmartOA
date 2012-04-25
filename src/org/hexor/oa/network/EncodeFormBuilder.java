package org.hexor.oa.network;

import java.net.URLEncoder;

public class EncodeFormBuilder extends PostMethod {
	private boolean mFirstQuery;

	public EncodeFormBuilder() {
		super("application/x-www-form-urlencoded");
		mFirstQuery = true;
	}

	public EncodeFormBuilder appendQueryParameter(String name, String content) {
		StringBuilder sb = new StringBuilder();
		if (mFirstQuery) {
			mFirstQuery = false;
		} else {
			sb.append("&");
		}
		sb.append(URLEncoder.encode(name)).append("=")
				.append(URLEncoder.encode(content));
		write(sb.toString().getBytes());
		return this;
	}

	@Override
	public void reset() {
		super.reset();
		mFirstQuery = true;
	}
}
