package cn.cdu.fanger.utill;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

public class CheckSD {
	
	/**
	 * 妫�煡瀛樺偍鍗℃槸鍚︽彃鍏�
	 * @return
	 */
	public static boolean isHasSdcard() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
	
 	/* 鑾峰緱鍥剧墖 鍥剧墖楂樺害 鏈�ぇmaxH
	 * @param imagePath
	 */
	Bitmap bitmap;
	int maxH = 200;//
	private void getImage(String imagePath,ImageView img){
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		// 鑾峰彇杩欎釜鍥剧墖鐨勫鍜岄珮
		bitmap = BitmapFactory.decodeFile(imagePath, options); // 姝ゆ椂杩斿洖bm涓虹┖
		// 璁＄畻缂╂斁姣�
		int be = (int) (options.outHeight / (float) maxH);
		int ys = options.outHeight % maxH;// 姹備綑鏁�
		float fe = ys / (float) maxH;
		if (fe >= 0.5)
			be = be + 1;
		if (be <= 0)
			be = 1;
		options.inSampleSize = be;

		// 閲嶆柊璇诲叆鍥剧墖锛屾敞鎰忚繖娆¤鎶妎ptions.inJustDecodeBounds 璁句负 false
		options.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeFile(imagePath, options);
		img.setImageBitmap(bitmap);
		img.setVisibility(View.VISIBLE);
	}
}
