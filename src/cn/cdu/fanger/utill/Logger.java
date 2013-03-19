package cn.cdu.fanger.utill;
import android.util.Log;


/**
 * 
 * Centralize all the logging in the application
 * 
 * 
 * can be disabled with the variable isActivated before publishing the app
 * 
 * @author me
 *
 */
public class Logger {


	/**
	 * Change this to activate/desactivate logging features in the application
	 */
	private static boolean isActivated = true;
	
	/**
	 * Change the default tag used by the application
	 */
	private static String defaultTAG = "androidAPP";


	/**
	 * Used for verbose logging, with default tag
	 * 
	 * @param text debug string
	 */
	public static void v(String text) {
		Logger.v(defaultTAG, text);
	}
	
	/**
	 * Used for verbose logging, with custom tag
	 * 
	 * @param TAG debub TAG
	 * @param text debug string
	 */
	public static void v(String TAG, String text) {
		if(isActivated) {
			Log.v(TAG, text);
		}
	}
	
	/**
	 * Used for error logging, with default tag
	 * 
	 * @param text debug string
	 */
	public static void e(String text) {
		Logger.e(defaultTAG, text); 
	}
	
	/**
	 * Used for error logging, with custom tag
	 * 
	 * @param TAG debub TAG
	 * @param text debug string
	 */
	public static void e(String TAG, String text) {
		if(isActivated) {
			Log.e(TAG, text);
		}
	}

	public static void i(String TAG, String text) {
		if(isActivated) {
			Log.i(TAG, text);
		}
	}
		
	public static void w(String TAG, String text) {
		if(isActivated) {
			Log.w(TAG, text);
		}
	}
}