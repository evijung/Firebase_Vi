package com.hitachi_tstv.mist.it.firebase_vi.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.hitachi_tstv.mist.it.firebase_vi.MainActivity;
import com.hitachi_tstv.mist.it.firebase_vi.R;
import com.hitachi_tstv.mist.it.firebase_vi.utility.MyAlertDialog;

/**
 * Created by Tunyaporn on 11/25/2017.
 */

public class RegisterFragment extends Fragment {
//Explicit

    private String tag = "25NovV1";
    private String nameString, emailString, passwordString;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //Create Toolbar
        createToolbar();

//        Create Menu Icon

        setHasOptionsMenu(true);
    }//Main Method

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemSave) {
            checkSpace();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkSpace() {

        Log.d(tag, "CheckSpace Work");


//        Initial View
        EditText nameEditText = getView().findViewById(R.id.edtName);
        EditText emailEditText = getView().findViewById(R.id.edtEmail);
        EditText passwordEditText = getView().findViewById(R.id.edtPassword);

//        Get Value From EditText
        nameString = nameEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

// Check Space

        if (nameString.isEmpty() || emailString.isEmpty() || passwordString.isEmpty()) {
//  Have Space

            MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
            myAlertDialog.myNormalDialog("Have Space", getString(R.string.sub_register));
        } else{
            // No Space
            updateFirebase();
        }

    }//Check Space

    private void updateFirebase() {
//        Setup ProgessDialog
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Please Wait.....");
        progressDialog.show();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //Success
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(),"Update Firebase Success",Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    //Non Success
                    MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                    myAlertDialog.myNormalDialog("Cannot Update Firebase",task.getException().getMessage());
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        inflater.inflate(R.menu.menu_save, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void createToolbar() {

//        Config Toolbar
        android.support.v7.widget.Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        // Setup Title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.register));
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.sub_register));

//        Back Icon
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager().popBackStack();

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }


} //Main Class
