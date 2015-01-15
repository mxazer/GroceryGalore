package com.mxazer.grocerygalore;

import android.app.Activity;
import android.os.Bundle;

import com.mxazer.grocerygalore.fragments.LoginFragment;

public class LoginActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.login_container, new LoginFragment()).commit();
		}
		
	}
}
