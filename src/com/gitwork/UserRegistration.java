package com.gitwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UserRegistration extends Activity implements OnClickListener{

	Button btRegister;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initialize();
		btRegister.setOnClickListener(this);
	}
	private void initialize() {
		// TODO Auto-generated method stub
		btRegister = (Button)findViewById(R.id.btnregister);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.btnregister:
			Intent startIntent = new Intent(UserRegistration.this,landingPage.class);
			startActivity(startIntent);
			break;
		}
	}
	

}
