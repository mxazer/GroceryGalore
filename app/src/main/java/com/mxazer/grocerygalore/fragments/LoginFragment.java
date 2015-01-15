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
import android.widget.TextView;

import com.mxazer.grocerygalore.MainActivity;
import com.mxazer.grocerygalore.R;
import com.mxazer.grocerygalore.SignUpActivity;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginFragment extends Fragment {

	protected EditText mUsername;
	protected EditText mPassword;
	protected Button mLoginButton;

	public LoginFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_login, container,
				false);

		TextView mSignUpTextView = new TextView(getActivity());
		mSignUpTextView = (TextView) rootView.findViewById(R.id.signUpText);
		mSignUpTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), SignUpActivity.class);
				getActivity().startActivity(intent);
			}
		});

		mUsername = (EditText) rootView.findViewById(R.id.usernameField);
		mPassword = (EditText) rootView.findViewById(R.id.passwordField);
		mLoginButton = (Button) rootView.findViewById(R.id.loginButton);

		mLoginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String username = mUsername.getText().toString();
				String password = mPassword.getText().toString();

				username = username.trim();
				password = password.trim();

				if (username.isEmpty() || password.isEmpty()) {
					// Empty field(s)
					AlertDialog.Builder builder = new AlertDialog.Builder(
							getActivity());
					builder.setMessage(R.string.login_error_message)
							.setTitle(R.string.login_error_title)
							.setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();
				} else {
					// Login
					ParseUser.logInInBackground(username, password,
							new LogInCallback() {

								@Override
								public void done(ParseUser user,
										ParseException e) {
									if (e == null) {
										// Success
										Intent intent = new Intent(
												getActivity(),
												MainActivity.class);
										intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
										intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
										startActivity(intent);
									} else {
										AlertDialog.Builder builder = new AlertDialog.Builder(
												getActivity());
										builder.setMessage(e.getMessage())
												.setTitle(
														R.string.login_error_title)
												.setPositiveButton(
														android.R.string.ok,
														null);
										AlertDialog dialog = builder.create();
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