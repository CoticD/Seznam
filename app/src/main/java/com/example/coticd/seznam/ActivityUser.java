package com.example.coticd.seznam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.User;

public class ActivityUser extends AppCompatActivity {

    ApplicationMy app;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        app = (ApplicationMy) getApplication();
        User user=app.getUser();
        username=(TextView)findViewById(R.id.UserName);

        username.setText(user.getVzdevek());

    }
}
