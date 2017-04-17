package com.example.song.myapplication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.song.myapplication.databinding.ActivityMainBinding;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        setContentView(R.layout.activity_main);
        binding.setMessage(new Message());
    }

    public static final String EXTRA_MESSAGE = "com.example.song.myapplication.MESSAGE";

    public void sendMesage(View view) {
        String url =   binding.getMessage().getMessage();

       final Intent intent = new Intent(this, DisplayMessageActivity.class);
       HttpGetAsyncTask h = new HttpGetAsyncTask((response) -> {
//       EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();

              intent.putExtra(EXTRA_MESSAGE,response.toString());
              startActivity(intent);

        });
        h.execute(url);
    }
}
