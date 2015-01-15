package com.mxazer.grocerygalore.fragments;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.mxazer.grocerygalore.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.ParseObject;

import java.util.List;

public class PatientFragment extends ListFragment {

    protected static final String TAG = PatientFragment.class.getSimpleName();
    protected List<ParseObject> mPatients;
    protected ParseUser mCurrentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_patients,
                container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Patient");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> patientList, ParseException e) {
                if (e == null) {
                    mPatients = patientList;

                    String[] patientIds = new String[mPatients.size()];
                    int i = 0;
                    for (ParseObject patient : mPatients) {
                        patientIds[i] = patient.getString("patientId");
                        i++;
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            getListView().getContext(),
                            android.R.layout.simple_list_item_1,
                            patientIds);
                    setListAdapter(adapter);
                } else {
                        Log.e(TAG, e.getMessage());
                        AlertDialog.Builder builder = new AlertDialog.Builder(
                                getListView().getContext());
                        builder.setMessage(e.getMessage())
                                .setTitle(R.string.error_title)
                                .setPositiveButton(android.R.string.ok, null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                }
            }
        });
    }
}
