package sample.application.calculator;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends Activity {
	
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_calculator, menu);
        return true;
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

    }*/
    
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
    
    public void operatorKeyOnClick(View v){
    	
    }
    
    
    

	/**
     * +が押されたとき
     */
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
     */
    public void equalKeyOnClick(View v){
    	Button button = (Button)v;
    	Log.d("[buttonのtext]", button.getText().toString());			//押されたボタンを取得
    	
    	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
    	this.sNum1 = tv.getText().toString();
    	num2 = Integer.parseInt(this.sNum1);
    	
    	this.sNum1 = Integer.toString(num1 + num2);
    	tv.setText(this.sNum1);
    }
    
    public String sNum1 = new String();
    public String strTemp = "";
    
}
