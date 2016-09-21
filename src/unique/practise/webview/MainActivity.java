package unique.practise.webview;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

	/**
	 * ����webview�ؼ�
	 */
	private WebView webview ;
	
	private ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		//�����ؼ�
		init();
		//����һ������
		String url = "http://book.360buy.com/10376716.html";
		//�����ݼ��ص�webview�ؼ���
		webview.loadUrl(url);
		//��ϵͳ��������������������
		webview.setWebViewClient(new WebViewClient(){
			
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				//
				view.loadUrl(url);
				return true;
			}
			
		});
		WebSettings settings =webview.getSettings();
		
		settings.setJavaScriptEnabled(true);
		
		webview.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// 
				if (newProgress==100) {
					//������Ϲر�
					closedialog();
				} else {
					//��
					opendialog(newProgress);
				}
				
				super.onProgressChanged(view, newProgress);
			}
		});
		
		
		
	}
	/**
	 * ��progressdialog
	 * @param newProgress
	 */
	protected void opendialog(int newProgress) {
		// 
		if(dialog==null){
			dialog = new ProgressDialog(MainActivity.this);
			dialog.setTitle("����");
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setProgress(newProgress);
			dialog.show();
			
		}
		else{
			dialog.setProgress(newProgress);
		}
	}
	/**
	 * �ر�progressdialog
	 */
	protected void closedialog() {
		// 
		if(dialog!=null&&dialog.isShowing()){
			dialog.dismiss();
			dialog=null;
			
		}
		
	}
	/**
	 * ���������
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 
		if(keyCode==KeyEvent.KEYCODE_BACK){
			
			if(webview.canGoBack()){
				webview.goBack();
				return true;
			}
			else{
				System.exit(0);
			}
			
			
		}
		
		
		
		return super.onKeyDown(keyCode, event);
	}
	
	
	/**
	 * ��ʼ���ؼ�
	 */
	private void init() {
		//��ʼ���ؼ�
		webview = (WebView) findViewById(R.id.web_view);
		
	}


}
