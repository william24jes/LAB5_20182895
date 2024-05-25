package com.example.lab5_20182895;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab5_20182895.databinding.ActivityAgregarTareaBinding;
import com.example.lab5_20182895.entity.Tarea;

import java.util.Calendar;
import java.util.Date;

public class AgregarTarea extends AppCompatActivity {

    private EditText titleEditText;
    private EditText descriptionEditText;
    private TextView selectedDateTextView;
    private TextView selectedTimeTextView;
    private Date selectedDate;
    ActivityAgregarTareaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAgregarTareaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        titleEditText = binding.taskTitle;
        descriptionEditText = binding.taskDescription;
        selectedDateTextView = binding.selectedDateText;
        selectedTimeTextView = binding.selectedTimeText;

        binding.selectDateButton.setOnClickListener(v -> showDatePickerDialog());
        binding.selectTimeButton.setOnClickListener(v -> showTimePickerDialog());

        Button saveTaskButton = binding.saveTaskButton;
        saveTaskButton.setOnClickListener(v -> saveTask());
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            selectedDate = calendar.getTime();
            selectedDateTextView.setText(selectedDate.toString());
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            selectedDate = calendar.getTime();  // actualizar selectedDate con la hora seleccionada
            selectedTimeTextView.setText(String.format("%02d:%02d", hourOfDay, minute));
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    private void saveTask() {
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        Tarea tarea = new Tarea(title, description, selectedDate);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("task", tarea);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
