package com.example.lab5_20182895;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab5_20182895.databinding.ActivityEditarTareaBinding;
import com.example.lab5_20182895.entity.Task;

import java.util.Date;

public class EditarTarea extends AppCompatActivity {

    private ActivityEditarTareaBinding binding;
    private EditText editTitleEditText;
    private EditText editDescriptionEditText;
    private TextView editSelectedDateTextView;
    private Date selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditarTareaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editTitleEditText = binding.editTaskTitle;
        editDescriptionEditText = binding.editTaskDescription;
        editSelectedDateTextView = binding.editSelectedDateText;

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("task")) {
            Task task = (Task) intent.getSerializableExtra("task");
            if (task != null) {
                editTitleEditText.setText(task.getTitle());
                editDescriptionEditText.setText(task.getDescription());
                editSelectedDateTextView.setText(task.getDueDate().toString());
            }
        }
    }
}
