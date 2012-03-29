package com.gitwork;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;

public class Calender extends Activity{
    Calendar datePicker;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calender);
		initialize();
	}
   
	public void initialize()
	{
      
	}
}
