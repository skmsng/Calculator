package sample.application.calculator;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends Activity {
	
	Integer num1,num2;

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
     * 数字が押されたとき
     */
    public void numKeyOnClick(View v){
    	Button button = (Button)v;
    	Log.d("[buttonのtext]", button.getText().toString());			//押されたボタンを取得
    	
    	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
    	
//    	Log.d("[tvのtext]", tv.getText().toString());					//ディスプレイの表示を取得
    	
    	tv.setText(tv.getText().toString() + button.getText().toString());

    }
    /**
     * +が押されたとき
     */
    public void addKeyOnClick(View v){
    	Button button = (Button)v;
    	Log.d("[buttonのtext]", button.getText().toString());			//押されたボタンを取得
    	
    	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
    	String sNum = tv.getText().toString();
    	num1 = Integer.parseInt(sNum);
    	
    	tv.setText("0");
    }
    
    /**
     * =が押されたとき
     */
    public void equalKeyOnClick(View v){
    	Button button = (Button)v;
    	Log.d("[buttonのtext]", button.getText().toString());			//押されたボタンを取得
    	
    	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
    	String sNum = tv.getText().toString();
    	num2 = Integer.parseInt(sNum);
    	
    	sNum = Integer.toString(num1 + num2);
    	tv.setText(sNum);
    }
    
    
    
}
