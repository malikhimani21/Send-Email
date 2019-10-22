package com.example.email;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText to, sub, message;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        to = findViewById(R.id.to);
        sub = findViewById(R.id.sub);
        message = findViewById(R.id.message);
        send = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String TO = to.getText().toString().trim();
                String SUB = sub.getText().toString().trim();
                String MESSAGE = message.getText().toString().trim();

                if (TO.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(TO).matches()) {
                    to.setError("Enter proper email address");
                }

                if (SUB.isEmpty()) {
                    sub.setError("Subject required");
                    sub.requestFocus();
                    return;
                }

                if (MESSAGE.isEmpty()) {
                    sub.setError("Message required");
                    sub.requestFocus();
                    return;
                }

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{TO});
                intent.putExtra(Intent.EXTRA_SUBJECT,SUB);
                intent.putExtra(Intent.EXTRA_TEXT,MESSAGE);
                intent.setType("plain/text");
                startActivity(Intent.createChooser(intent,"Send mail"));
            }
        });
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Alert");
        builder.setMessage("Do you want to close app...?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
