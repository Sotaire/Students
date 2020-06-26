package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    String name;
    String secondName;
    String group;
    Date year;

    int year1;
    int month1;
    int day1;

    TextView dateTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Log.d("new", "oncreate");

        dateTV = findViewById(R.id.tv_add_dateOfBirth);

        EditText nameET = findViewById(R.id.et_add_name);
        EditText secondNameET = findViewById(R.id.et_add_secondName);
        EditText groupET = findViewById(R.id.et_add_group);

        nameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                name = s.toString();
                Log.d("new", "change");
            }
        });

        secondNameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                secondName = s.toString();
                Log.d("new", "change");
            }
        });

        groupET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                group = s.toString();
                Log.d("new", "change");
            }
        });


        Button chooseDate = findViewById(R.id.btn_add_dateOfBirth);
        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        dateTV.setText(dayOfMonth + "." + (month + 1) + "." + year);
                        AddActivity.this.year = new Date((year - 1900), month, dayOfMonth);

                    }
                }, year, month, day);
                dialog.show();
            }
        });
        Button save = findViewById(R.id.btn_add_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.name = name;
                student.secondName = secondName;
                student.group = group;
                student.dateOfBirth = year;

                Intent intent = new Intent();
                intent.putExtra("student", student);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            Student student = (Student) intent.getSerializableExtra("student");
            if (student != null) {
                nameET.setText(student.name);
                secondNameET.setText(student.secondName);
                groupET.setText(student.group);

                DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
                dateTV.setText(format.format(student.dateOfBirth));
                year = student.dateOfBirth;
            }
        }
    }
}