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
	 * 声明webview控件
	 */
	private WebView webview ;
	
	private ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		//声明控件
		init();
		//创建一个链接
		String url = "http://book.360buy.com/10376716.html";
		//把数据加载到webview控件中
		webview.loadUrl(url);
		//用系统或其他浏览器浏览该链接
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
					//加载完毕关闭
					closedialog();
				} else {
					//打开
					opendialog(newProgress);
				}
				
				super.onProgressChanged(view, newProgress);
			}
		});
		
		
		
	}
	/**
	 * 打开progressdialog
	 * @param newProgress
	 */
	protected void opendialog(int newProgress) {
		// 
		if(dialog==null){
			dialog = new ProgressDialog(MainActivity.this);
			dialog.setTitle("加载");
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setProgress(newProgress);
			dialog.show();
			
		}
		else{
			dialog.setProgress(newProgress);
		}
	}
	/**
	 * 关闭progressdialog
	 */
	protected void closedialog() {
		// 
		if(dialog!=null&&dialog.isShowing()){
			dialog.dismiss();
			dialog=null;
			
		}
		
	}
	/**
	 * 更改物理键
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
	 * 初始化控件
	 */
	private void init() {
		//初始化控件
		webview = (WebView) findViewById(R.id.web_view);
		
	}


}
