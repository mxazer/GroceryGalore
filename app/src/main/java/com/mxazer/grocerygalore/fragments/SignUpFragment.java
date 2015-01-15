package com.mxazer.grocerygalore.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mxazer.grocerygalore.MainActivity;
import com.mxazer.grocerygalore.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpFragment extends Fragment {

	protected EditText mUsername;
	protected EditText mPassword;
	protected EditText mEmail;
	protected Button mSignUpButton;

	public SignUpFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_sign_up, container,
				false);

		mUsername = (EditText) rootView.findViewById(R.id.usernameField);
		mPassword = (EditText) rootView.findViewById(R.id.passwordField);
		mEmail = (EditText) rootView.findViewById(R.id.emailField);
		mSignUpButton = (Button) rootView.findViewById(R.id.signupButton);

		mSignUpButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String username = mUsername.getText().toString();
				String password = mPassword.getText().toString();
				String email = mEmail.getText().toString();

				username = username.trim();
				password = password.trim();
				email = email.trim();

				if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							getActivity());
					builder.setMessage(R.string.signup_error_message)
							.setTitle(R.string.signup_error_title)
							.setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();
				} else {
					// create new user and add to parse
					ParseUser user = new ParseUser();
					user.setUsername(username);
					user.setPassword(password);
					user.setEmail(email);

					user.signUpInBackground(new SignUpCallback() {

						@Override
						public void done(ParseException e) {
							if (e == null) {
								Intent intent = new Intent(getActivity(),
										MainActivity.class);
								intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
								startActivity(intent);
							} else {
								AlertDialog.Builder builder = new AlertDialog.Builder(
										getActivity());
								builder.setMessage(e.getMessage())
										.setTitle(R.string.signup_error_title)
										.setPositiveButton(android.R.string.ok,
												null);
								AlertDialog dialog =  builder.create();
								dialog.show();
							}
						}
					});
				}
			}
		});

		return rootView;
	}
}