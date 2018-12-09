package com.example.anamika.androidqrcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttonScan;
    private TextView statusInfo, qrInfo;
    private IntentIntegrator qrScan;
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        setTitle("Profile Page");
        String userBio = getIntent().getExtras().getString("USER_BIO");
        Gson gson = ((CustomApplication)getApplication()).getGsonObject();
        UserObject mUserObject = gson.fromJson(userBio, UserObject.class);
        String bio = mUserObject.getUsername() + "\n" +
                mUserObject.getEmail() + "\n" +
                mUserObject.getPhone() + "\n" +
                mUserObject.getAddress() + "\n" +
                mUserObject.getPassword();
        TextView userTextValue = (TextView)findViewById(R.id.user_bio);
        userTextValue.setText(bio);
        //View objects
        buttonScan = (Button) findViewById(R.id.buttonScan);
        statusInfo = (TextView) findViewById(R.id.status_info);
        qrInfo = (TextView) findViewById(R.id.qrcode_info);

        qrScan = new IntentIntegrator(this);

        //attaching onclick listener
        buttonScan.setOnClickListener(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews
                    //textViewName.setText(obj.getString("name"));
                    //textViewAddress.setText(obj.getString("address"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    statusInfo.setText("Success");
                    qrInfo.setText(result.getContents());
                    //textViewAddress.setText(obj.getString("address"));
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View view) {
        qrScan.initiateScan();
    }
}
