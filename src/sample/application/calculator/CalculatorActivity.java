package sample.application.calculator;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends Activity {
	
    public String sNum1 = new String();
    public String strTemp = "";
    public String strResult = "0";
    public Integer operator = 0;
    
/*
    public static String s1;
    //初期化
    static{
    	s1 = "test";
    }
*/	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        
        this.readPreferences();
    }

    
    @Override
	protected void onStop() {
		super.onStop();
		
		this.writePreferences();
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_calculator, menu);
        return true;
    }
    

    
    /**
     * 数字が押されたとき（テキスト）
     */
    public void numKeyOnClick(View v){
    	
    	//CharSequence（親）をString（子）にキャストできる？androidの変な仕様？
    	String strInKey = (String)((Button)v).getText();
    	
    	if(strInKey.equals(".")){
    		if(this.strTemp.length()==0){
	    		this.strTemp = "0.";
	    	}else{
	    		if(this.strTemp.indexOf(".") == -1){
	    			this.strTemp = this.strTemp + ".";
	    		}
	    	}
	    }else{
	    	this.strTemp = this.strTemp + strInKey;
	    }
	    
	    this.showNumber(this.strTemp);
    }
    
    /**
     * ディスプレイに表示するメソッド
     */
    private void showNumber(String strNum) {
    	
    	DecimalFormat form = new DecimalFormat("#,##0");
    	String strDecimal = "";
    	String strInt = "";
    	String fText = "";
    	
    	if(strNum.length() > 0){
    		int decimalPoint = strNum.indexOf(".");
    		if(decimalPoint > -1){
    			strDecimal = strNum.substring(decimalPoint);
    			strInt = strNum.substring(0,decimalPoint);
    		}else{
    			strInt = strNum;
    		}
    		fText = form.format(Double.parseDouble(strInt)) + strDecimal;
    		
    	}else{
    		fText = "0";
    	}
    	
    	((TextView)findViewById(R.id.displayPanel)).setText(fText);
	}
    
    public void functionKeyOnClick(View v){
    	switch(v.getId()){
    	case R.id.keypadAC:
    		this.strTemp = "";
    		this.strResult = "0";
    		this.operator = 0;
    		break;
    	case R.id.keypadC:
    		this.strTemp = "";
    		break;
    	case R.id.keypadBS:
    		if(this.strTemp.length() == 0){
    			return;
    		}else{
    			this.strTemp = this.strTemp.substring(0, this.strTemp.length()-1);
    			break;
    		}
    	case R.id.keypadCopy:
    		ClipboardManager cm = (ClipboardManager)this.getSystemService(CLIPBOARD_SERVICE);
    		cm.setText(((TextView)this.findViewById(R.id.displayPanel)).getText());
    	return;
    	}
    	this.showNumber(this.strTemp);
    }
    
    /**
     * +,-,*,/,= が押されたとき
     */
    public void operatorKeyOnClick(View v){
    	
    	if(operator != 0){
    		if(this.strTemp.length() > 0){
    			this.strResult = this.doCalc();
    			this.showNumber(this.strResult);
    		}
    	}else{
    		if(this.strTemp.length() > 0){
    			this.strResult = this.strTemp;
    		}
    	}
    	
    	this.strTemp = "";
    	
    	if(v.getId() == R.id.keypadEq){
    		this.operator = 0;
    	}else{
    		this.operator = v.getId();
    	}
    }
    
    /**
     * 計算
     */
	private String doCalc() {
		BigDecimal bd1 = new BigDecimal(strResult);
		BigDecimal bd2 = new BigDecimal(strTemp);
		BigDecimal result = BigDecimal.ZERO;
		
		switch(this.operator){
		case R.id.keypadAdd:
			result = bd1.add(bd2);
			break;
		case R.id.keypadSub:
			result = bd1.subtract(bd2);
			break;
		case R.id.keypadMulti:
			result = bd1.multiply(bd2);
			break;
		case R.id.keypadDiv:
			if(!bd2.equals(BigDecimal.ZERO)){
				result = bd1.divide(bd2, 12, 3);
			}else{
				Toast toast = Toast.makeText(this, R.string.toast_div_by_zero, 1000);
				toast.show();
			}
			break;
		}
		
		if(result.toString().indexOf(".") >= 0){
			return result.toString().replaceAll("¥¥.0+$|0+$", "");
//			return result.toString().replaceAll("¥.0+$", "");
		}else{
			return result.toString();
		}
	}
	
	private void writePreferences(){
		SharedPreferences prefs = getSharedPreferences("CalcPrefs", MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("strTemp", strTemp);
		editor.putString("strResult", strResult);
		editor.putInt("operator", operator);
		editor.putString("strDisplay", ((TextView)this.findViewById(R.id.displayPanel)).getText().toString());
		editor.commit();		
	}
	
	private void readPreferences(){
		SharedPreferences prefs = getSharedPreferences("CalcPrefs", MODE_PRIVATE);
		this.strTemp = prefs.getString("strTemp", "");
		this.strResult = prefs.getString("strResult", "0");
		operator = prefs.getInt("operator", 0);
		((TextView)this.findViewById(R.id.displayPanel)).setText(prefs.getString("strDisplay", "0"));
	}
	
	

    /**
     * 数字が押されたとき（自作）
     *
    public void numKeyOnClick(View v){
    	Button button = (Button)v;
    	Log.d("[buttonのtext]", button.getText().toString());			//押されたボタンを取得
    	
    	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
    	
//    	Log.d("[tvのtext]", tv.getText().toString());					//ディスプレイの表示を取得
    	
    	tv.setText(tv.getText().toString() + button.getText().toString());
    }
	/**
     * +が押されたとき
     *
    Integer num1,num2;
    public void addKeyOnClick(View v){
    	Button button = (Button)v;
    	Log.d("[buttonのtext]", button.getText().toString());			//押されたボタンを取得
    	
    	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
    	this.sNum1 = tv.getText().toString();
    	num1 = Integer.parseInt(this.sNum1);
    	
    	tv.setText("0");
    }
    /**
     * =が押されたとき
     *
    public void equalKeyOnClick(View v){
    	Button button = (Button)v;
    	Log.d("[buttonのtext]", button.getText().toString());			//押されたボタンを取得
    	
    	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
    	this.sNum1 = tv.getText().toString();
    	num2 = Integer.parseInt(this.sNum1);
    	
    	this.sNum1 = Integer.toString(num1 + num2);
    	tv.setText(this.sNum1);
    }*/
    
}
