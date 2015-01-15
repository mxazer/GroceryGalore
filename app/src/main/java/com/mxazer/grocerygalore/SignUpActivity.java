package com.mxazer.grocerygalore;

import android.app.Activity;
import android.os.Bundle;

import com.mxazer.grocerygalore.fragments.SignUpFragment;

public class SignUpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.sign_up_container, new SignUpFragment()).commit();
		}

	}
}
