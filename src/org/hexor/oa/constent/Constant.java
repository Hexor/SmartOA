package org.hexor.oa.constent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.os.Environment;
import android.util.Log;

final public class Constant {
	public static final int TYPE_TAB_FACE_MARK = 1;
	public static final int TYPE_TAB_TIME = 0;
	public static final int TYPE_TAB_LOCAL = 2;

	public static final int TYPE_ACTION_FACENOMARK = 1;

	public static boolean mHasProxy = false;
	public static String mProxyHost = null;
	public static int mProxyPort = 0;

	// private static final String SERVICE_API_PREFIX =
	// "http://58.215.180.102/api/";
	// private static final String SERVICE_API_PREFIX =
	// "http://58.215.180.101/api_v1/";
	private static final String EXTERNAL_URL_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath()
			+ "/.sm_external_url";
	private static final String SERVICE_API_PREFIX;

	public static final String URL_SMARTALBUM_HOMEPAGE = "http://f.sdo.com";
	public static final String URL_SMARTALBUM_WEIBO = "http://weibo.cn/smartalbum";

	public static final String URL_FILE_USERFEEDBACK;
	public static final String URL_FILE_UPLOAD;
	public static final String URL_FILE_GROUP;
	public static final String URL_FILE_MODIFY;
	public static final String URL_FILE_NOTFACE;
	public static final String URL_FILE_NOTGROUP;
	public static final String URL_FILE_LOGIN;
	public static final String URL_FILE_VERSION;
	public static final String URL_FILE_LOG;

	public static final String FILE_NAME_UPDATE = "albumupdatemark";
	public static final String FILE_NAME_NOTFACE = "albumnotface";
	public static final String FILE_NAME_NOTGROUP = "albumnotgroup";

	public static final String FILE_PATH_EXTERNAL = "sndaalbum";

	public static final double FACE_ZOOM_NUM = 1.875;

	public static final String K_READY = "index_ready";
	public static final String K_COMPLETE = "com_complete";
	public final static String K_REVISION = "revision_hash";
	public final static String K_INTRO = "new_intro";
	public final static String K_GUIDE1 = "guide_1";
	public final static String K_GUIDE2 = "guide_2";

	public static final int SETTING_TYPE_DELETE = 1;
	public static final int SETTING_TYPE_HIDE = 2;
	public static final int SETTING_TYPE_UNHIDE = 3;

	// 修改姓名的type
	public static final int SETTING_NAME_GROUP = 1;// 修改整个组的姓名
	public static final int SETTING_NAME_ONE = 2;// 修改单张的姓名

	public static final int MENU_MUTL_NONE = 2;
	public static final int MENU_MUTL_MULTI = 3;
	public static final int MENU_MUTL_EDIT = 1;

	public static final int MOBILE_DIREC_PORTAIT = 1;
	public static final int MOBILE_DIREC_LANDS = 2;

	public static final int FACE_UPLOAD_RUNNING = 1;
	public static final int FACE_UPLOAD_FINISH = 2;

	public static final int FACE_DETECT_RUNNING = 1;
	public static final int FACE_DETECT_FINISH = 2;

	public static int TYPE_OF_SPLASH = 0;
	public static int TYPE_OF_SETTING = 1;

	public static String SYS_MMS_URI = "content://mms/part";

	public static final int TYPE_LOCAL_GATOGERY = 0;
	public static final int TYPE_TIMED_GATOGERY = 1;
	public static final int TYPE_FACED_GATOGERY = 2;
	public static final int TYPE_FACED_UNNAME_CATEGORY = 3;
	public static final int TYPE_SYSTEM_INTENT_CATEGORY = 4;

	public final static String PREF_WIFI = "bwifi";
	public final static String PREF_HEAD = "bhead";
	public final static String PREF_FRAME = "bframe";
	public final static String PREF_DATE = "scandate";
	public final static String PREF_VISITNUM = "visitnum";
	public final static String PREF_SESSIONID = "sessionid";
	public final static String NETWORK_NAME_WIFI = "wifi";

	public static final String PREF_NAME_AUTH = "authedinfo";
	public static final String PREF_NAME_LOGIN = "logininfo";
	public static final String PREF_NAME_REG = "registinfo";

	public static final String SNDA_ALBUM_APPID = "800028600";

	public static final String CAMERA_IMAGE_BUCKET_NAME = Environment
			.getExternalStorageDirectory().toString() + "/DCIM/100media";
	public static final String CAMERA_IMAGE_BUCKET_ID = getBucketId(CAMERA_IMAGE_BUCKET_NAME);

	public static final String CAMERA_IMAGEOS_BUCKET_NAME = Environment
			.getExternalStorageDirectory().toString() + "/DCIM/Camera";
	public static final String CAMERA_IMAGEOS_BUCKET_ID = getBucketId(CAMERA_IMAGEOS_BUCKET_NAME);

	public static final String CAMERA_ANDRO_BUCKET_NAME = Environment
			.getExternalStorageDirectory().toString() + "/DCIM/100ANDRO";
	public static final String CAMERA_ANDRO_BUCKET_ID = getBucketId(CAMERA_ANDRO_BUCKET_NAME);

	public static final String CAMERA_SHARP_BUCKET_NAME = Environment
			.getExternalStorageDirectory().toString() + "/DCIM/100SHARP";
	public static final String CAMERA_SHARP_BUCKET_ID = getBucketId(CAMERA_SHARP_BUCKET_NAME);

	/**
	 * Matches code in MediaProvider.computeBucketValues. Should be a common
	 * function.
	 */
	private static String getBucketId(String path) {
		return String.valueOf(path.toLowerCase().hashCode());
	}

	static {
		String defaultUrl = "http://58.215.180.101/api_v1/";
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(EXTERNAL_URL_PATH));
				defaultUrl = reader.readLine().trim();
				if(!defaultUrl.endsWith("/")) {
					defaultUrl = defaultUrl + "/";
				}
				Log.d("smartalbum:Constant", "set api url to " + defaultUrl);
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
			}
		}
		SERVICE_API_PREFIX = defaultUrl;
		URL_FILE_USERFEEDBACK = SERVICE_API_PREFIX
				+ "feedback.php";
		URL_FILE_UPLOAD = SERVICE_API_PREFIX + "upload";
		URL_FILE_GROUP = SERVICE_API_PREFIX
				+ "face_info_new";
		URL_FILE_MODIFY = SERVICE_API_PREFIX
				+ "modify/update";
		URL_FILE_NOTFACE = SERVICE_API_PREFIX
				+ "modify/notface";
		URL_FILE_NOTGROUP = SERVICE_API_PREFIX
				+ "modify/notingroup";
		URL_FILE_LOGIN = SERVICE_API_PREFIX + "login";
		URL_FILE_VERSION = SERVICE_API_PREFIX
				+ "chk_version";
		URL_FILE_LOG = SERVICE_API_PREFIX
				+ "upload_xml.php";
	}

}
