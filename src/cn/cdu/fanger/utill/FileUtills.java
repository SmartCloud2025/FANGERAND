package cn.cdu.fanger.utill;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Environment;

public class FileUtills {
	private String SDCardRoot;
	
	public FileUtills(){
		SDCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
	}
	/**
	 * 鍦╯d鍗′笂鍒涘缓鐩綍
	 * @param dir
	 * @return
	 * @throws IOException 
	 */
	public File creatDir(String dir) throws IOException{
		
			File file = new File(SDCardRoot + dir + File.separator);
			file.mkdir();
			return file;	
			
	}
	
	public File creatFile(String dir,String fileName) throws IOException {
			File file = new File(SDCardRoot + dir + File.separator+fileName);
			file.createNewFile();
			
			return file;	
		
	}
	/**
	 * 鍒ゆ柇鎸囧畾鏂囦欢鏄惁瀛樺湪
	 * @param dir
	 * @param fileName
	 * @return
	 */
	public boolean isFileExit(String dir,String fileName){
		
		File file  = new File(SDCardRoot+dir+File.separator+fileName);
		return file.exists();
	}
	/**
	 * 灏嗕竴涓猧nputstream閲岄潰鐨勬暟鎹啓鍏ュ埌sd鍗�
	 * @param path
	 * @param fileName
	 * @param input
	 * @return
	 */
	public File writeToSDFromInput(String path,String fileName,InputStream input){
		File file = null;
		OutputStream outputStream = null;
		try {
			creatDir(path);
			file = creatFile(path, fileName);
			outputStream = new FileOutputStream(file);
			byte buffer[] = new byte[4 * 1024];
			int temp;
			while((temp=input.read(buffer)) != -1){
				outputStream.write(buffer,0,temp);
			}
			outputStream.flush();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			try {
				outputStream.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		
		}
		return file;
	}
	
	
	public static String getStringFromStream(InputStream iso) throws IOException{
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		try {
			buffer = new BufferedReader(new InputStreamReader(iso));
			while ( (line = buffer.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buffer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}  

	
	/**
	 * 
	 */
//	public List<Mp3Info> getMp3Files(String mp3path){
//		
//			return this.getMP3List(mp3path);
//		
//		
//	}
//	public String getLRCName(String mp3Name){
//		
//		String tempt = mp3Name.substring(0, mp3Name.length()-4);
//		String lrcString = tempt + ".lrc";
//		return lrcString;
//	}
//	public List<Mp3Info> getMP3List(String mp3path){
//		List<Mp3Info> mp3Infos = new ArrayList<Mp3Info>();
//		File file = new File(SDCardRoot+File.separator+mp3path);
//		
//		File []files = file.listFiles();
//	
//		for(int i = 0;i < files.length;i++){
//			
//			if (files[i].getName().endsWith("mp3")) {
//				Mp3Info info = new Mp3Info();
//				info.setMp3Name(files[i].getName());
//				info.setMp3Size(files[i].length()+"");	
//				info.setLrcName(getLRCName(files[i].getName()));
//				mp3Infos.add(info);
//				
//			}
//		}
//		return mp3Infos;
//	}
//	
//	/**
//	 * 鐙mp3peizhi鐨勫瓨鏀捐矾寰�
//	 * @return
//	 */
//	public String readMp3epath(){
//		String path = null;
//		try {
//			//璇诲嚭鏉�
//			FileReader fileReader = new FileReader(Constant.Setting.SETTING_PATH);
//			BufferedReader bReader = new BufferedReader(fileReader);
//			path = bReader.readLine();
//			bReader.close();	
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}
//		return path;
//	}
	
	
}









