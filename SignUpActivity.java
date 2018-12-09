package com.example.anamika.androidqrcodescanner;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import android.content.Intent;


import android.support.v7.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = SignUpActivity.class.getSimpleName();
    private TextView displayError;
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText ug_no;
    private EditText branch_info;
    private RadioGroup radioGroup;
    private boolean loginOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Android Fingerprint Registration");
        username = (EditText)findViewById(R.id.username);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        ug_no = (EditText)findViewById(R.id.ug_no);
        branch_info = (EditText)findViewById(R.id.branch);
        radioGroup = (RadioGroup)findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == R.id.with_fingerprint){
                    loginOption = false;
                }
                if(id == R.id.with_fingerprint_and_password){
                    loginOption = true;
                }
            }
        });
        Button signUpButton = (Button) findViewById(R.id.sign_up_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameValue = username.getText().toString();
                String emailValue = email.getText().toString();
                String passwordValue = password.getText().toString();
                String ugNo = ug_no.getText().toString();
                String branch = branch_info.getText().toString();
                int selectedButtonId = radioGroup.getCheckedRadioButtonId();
                if(TextUtils.isEmpty(usernameValue) || TextUtils.isEmpty(emailValue)|| TextUtils.isEmpty(passwordValue)
                        || TextUtils.isEmpty(ugNo) || TextUtils.isEmpty(branch)){
                    Toast.makeText(SignUpActivity.this, "All input fields must be filled", Toast.LENGTH_LONG).show();
                }else if(selectedButtonId == -1){
                    Toast.makeText(SignUpActivity.this, "Login option must be selected", Toast.LENGTH_LONG).show();
                }else{
                    Gson gson = ((CustomApplication)getApplication()).getGsonObject();
                    UserObject userData = new UserObject(usernameValue, emailValue, passwordValue, ugNo, branch, loginOption);
                    String userDataString = gson.toJson(userData);
                    CustomSharedPreference pref = ((CustomApplication)getApplication()).getShared();
                    pref.setUserData(userDataString);
                    username.setText("");
                    email.setText("");
                    password.setText("");
                    ug_no.setText("");
                    branch_info.setText("");
                    Intent loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                }
            }
        });
    }
}
