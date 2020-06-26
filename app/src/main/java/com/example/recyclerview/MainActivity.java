package com.example.recyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IStudentListener {

    MainAdapter adapter;

    private final int ADD_ACTIVITY = 42;

    ArrayList <Student> newList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycle_view);

        adapter = new MainAdapter();
        adapter.listener = this;

        recyclerView.setAdapter(adapter);

    }

    public void addElement(View view){
        Intent intent = new Intent(this,AddActivity.class);
        startActivityForResult(intent,ADD_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ACTIVITY && resultCode == RESULT_OK){
            Student student = (Student) data.getSerializableExtra("student");
            adapter.addElements(student);
            Log.d("new","result");
        }
        if (requestCode == 43 && resultCode == RESULT_OK){
            Log.d("new","student2");
            Student student = (Student) data.getSerializableExtra("student");
            newList.add(student);

            Changes changes = new Changes(adapter.data,newList);
            DiffUtil.DiffResult resulted =  DiffUtil.calculateDiff(changes);

            adapter.data = newList;
            resulted.dispatchUpdatesTo(adapter);
        }
    }


    @Override
    public void onStudentClick(Student student) {
        Intent intent = new Intent(this,AddActivity.class);
        intent.putExtra("student",student);
        startActivityForResult(intent,43);

    }
}