package cn.cdu.fanger.ac.view;

/**
 * 
 * @author Lee
 *
 */
public interface AsyncActivity {

	public void showLoadingProgressDialog();

	public void showProgressDialog(CharSequence message);

	public void dismissProgressDialog();

}
