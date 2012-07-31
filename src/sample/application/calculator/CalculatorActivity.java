package sample.application.calculator;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    
    public void numKeyOnClick(View v){
    	Button button = (Button)v;
    	
    	Log.d("buttonのtext", button.getText().toString());
    	//前回の画面を残す
//    	TextView et = (TextView) this.findViewById(R.id.displayPanel);	//今の画面（インスタンス）の取得
//        SharedPreferences pref = this.getSharedPreferences("MemoPrefs", MODE_PRIVATE);	//前回のを取得？
//        et.setText(pref.getString("memo", ""));	//前回のをセット
//        et.setSelection(pref.getInt("cursor", 0));
//    	aaa
    }
}
