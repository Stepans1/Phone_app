package com.example.myapplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.data.NetworkTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//I couldn't implement : Request pagination - load enough items to populate the list and
// load more items every time the user scrolls to the end of the list (limit/offset);
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class MainActivity extends AppCompatActivity {

    private List<String>data=new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize the activity
        Context context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the EditText in the layout
        EditText editText = findViewById(R.id.editText);


        recyclerView = findViewById(R.id.recycle_gif);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);









        // Text change listener for the EditText
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Do nothing before text changes
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Wait for 300 milliseconds before executing code after text change
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // Get the new text
                String newText = charSequence.toString();

                // Run a background task for the new text
                ExecutorService executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.getMainLooper());
                NetworkTask networkTask = new NetworkTask(newText, context);
                //networkTask.setValue(newText);
                executor.execute(() -> {
                    // Background work
                   data= networkTask.getData();
                    // UI Thread work (updating the user interface)
                    handler.post(() -> {
                        networkTask.updateUI();
                    });
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Do nothing after text changes
            }
        });


    }
}
