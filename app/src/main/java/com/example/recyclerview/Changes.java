package com.example.recyclerview;

import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;

public class Changes  extends DiffUtil.Callback {

    private final ArrayList<Student> oldList;
    private final ArrayList<Student> newList;

   public Changes(ArrayList<Student>oldList, ArrayList<Student> newList) {
         this.oldList = oldList;
         this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Student oldStudent = oldList.get(oldItemPosition);
        Student newStudent = newList.get(newItemPosition);
        return true;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Student oldStudent = oldList.get(oldItemPosition);
        Student newStudent = newList.get(newItemPosition);
        return oldStudent.dateOfBirth.equals(newStudent.dateOfBirth) &&  oldStudent.name.equals(newStudent.name) && oldStudent.secondName.equals(newStudent.secondName) && oldStudent.group.equals(newStudent.group);
    }
}
