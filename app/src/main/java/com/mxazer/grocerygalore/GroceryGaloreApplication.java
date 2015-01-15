package com.mxazer.grocerygalore;

import android.app.Application;

import com.parse.Parse;

public class GroceryGaloreApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Parse.initialize(this, "Uqce3qPGyFOeeRx0BCFua8Y52lcU2o8OAOfyNX06",
				"aaEME0oUE51wtk5MeBrZwfrkDew7PpMX7IFTO10v");
		
	}
}
