package org.hexor.oa.constent;

public final class Env {
	public enum Model {
		NORMAL, S1,
	}

	final public static Model MODEL;

	static {
		if (android.os.Build.MANUFACTURER.equals("snda.com")) {
			MODEL = Model.S1;
		} else {
			MODEL = Model.NORMAL;
		}
	}

	public static String cacheDirectory = null;
	
	public  static void setCacheDirectory(String s){
		cacheDirectory =s;
	}
	
	public  static String getCacheDirectory(){
		return cacheDirectory;
	}
}
