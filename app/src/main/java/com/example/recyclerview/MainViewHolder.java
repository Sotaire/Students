package com.example.recyclerview;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainViewHolder extends RecyclerView.ViewHolder {

    TextView nameTV;
    TextView secondNameTV;
    TextView groupTV;
    TextView yearTV;

    public IStudentListener listener;

    Student student;

    public MainViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTV = itemView.findViewById(R.id.tv_vh_name);
        secondNameTV = itemView.findViewById(R.id.tv_vh_secondName);
        groupTV = itemView.findViewById(R.id.tv_vh_group);
        yearTV = itemView.findViewById(R.id.tv_vh_year);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onStudentClick(student);
            }
        });
    }

    public void onBind(Student student){
        nameTV.setText(student.name);
        secondNameTV.setText(student.secondName);
        groupTV.setText(student.group);
        this.student = student;

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        yearTV.setText(format.format(student.dateOfBirth));
    }

}
