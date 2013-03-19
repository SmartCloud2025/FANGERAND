package cn.cdu.fanger.model;

import java.util.logging.Logger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;



/**
 * 浣跨敤  SharedPreference file 鏆傚瓨鏁版嵁
 * @author me
 *
 */
public class PrefHelper {

	private static PrefHelper	mInstance;


	private SharedPreferences 	mPref;
	private Editor   			mEdit;


	private PrefHelper(Context c) {
		mPref = c.getSharedPreferences(PrefNames.PREF_NAME, Application.MODE_PRIVATE);
		mEdit = mPref.edit();
		System.out.println("------1");
	}
	
	public boolean isBlueColor() {
		//default color is blue
		return mPref.getBoolean(PrefNames.BOOL_ISBLUECOLOR, true);
	}

	public void setColor(boolean setBlueColor) {
		mEdit.putBoolean(PrefNames.BOOL_ISBLUECOLOR, setBlueColor);
		mEdit.commit();
	}
	
	public static void init(Context context) {
		mInstance = new PrefHelper(context);
		System.out.println("------2");
	}

	public static PrefHelper getInstance() {
		if(null != mInstance) {
			return mInstance;
		}
		else {
			System.out.println("package cn.cdu.fanger.model");
		}
		return null;
	}
	
	

}
