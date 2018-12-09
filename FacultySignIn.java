package com.example.anamika.androidqrcodescanner;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Date;

public class FacultySignIn extends AppCompatActivity {
    private static final String TAG = SignUpActivity.class.getSimpleName();
    private TextView displayError;
    private EditText username;
    private EditText subject_code;
    private EditText password;
    private EditText ug_no;
    private EditText branch_info;
    private RadioGroup radioGroup;
    private boolean loginOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_sign_in);
        setTitle("Android Fingerprint Registration");
        username = (EditText)findViewById(R.id.username);
        subject_code = (EditText)findViewById(R.id.subject_code);
        Button generateQRCode = (Button) findViewById(R.id.generate_qr_code_button);
        generateQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameValue = username.getText().toString();
                String subjectCode = subject_code.getText().toString();
                if(TextUtils.isEmpty(usernameValue) || TextUtils.isEmpty(subjectCode)) {
                    Toast.makeText(FacultySignIn.this, "All input fields must be filled", Toast.LENGTH_LONG).show();
                }
                else{
                    Gson gson = ((CustomApplication)getApplication()).getGsonObject();
                    String dateTime = "";
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                    try {
                        Date date = new Date();
                        dateTime = dateFormat.format(date);
                        System.out.println("Current Date Time : " + dateTime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    FacultyObject facultyObject = new FacultyObject(usernameValue, subjectCode, dateTime);
                    String facultyDataString = gson.toJson(facultyObject);
                    CustomSharedPreference pref = ((CustomApplication)getApplication()).getShared();
                    pref.setFacutyData(facultyDataString);
                    username.setText("");
                    subject_code.setText("");
                    Intent loginIntent = new Intent(FacultySignIn.this, QRCode.class);
                    startActivity(loginIntent);
                }
            }
        });
    }
}
