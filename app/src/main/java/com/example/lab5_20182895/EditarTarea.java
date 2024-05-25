package com.example.lab5_20182895;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab5_20182895.databinding.ActivityEditarTareaBinding;
import com.example.lab5_20182895.entity.Task;

import java.util.Calendar;
import java.util.Date;

public class EditarTarea extends AppCompatActivity {

    private ActivityEditarTareaBinding binding;
    private EditText editTitleEditText;
    private EditText editDescriptionEditText;
    private TextView editSelectedDateTextView;
    private Date selectedDate;
    private Task task;

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
            task = (Task) intent.getSerializableExtra("task");
            if (task != null) {
                editTitleEditText.setText(task.getTitulo());
                editDescriptionEditText.setText(task.getDescripcion());
                selectedDate = task.getFecha();
                editSelectedDateTextView.setText(selectedDate.toString());
            }
        }

        binding.editSelectDateButton.setOnClickListener(v -> showDatePickerDialog());
        binding.cancelButton.setOnClickListener(v -> finish());

    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDate != null ? selectedDate : new Date());

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            selectedDate = calendar.getTime();
            editSelectedDateTextView.setText(selectedDate.toString());
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
