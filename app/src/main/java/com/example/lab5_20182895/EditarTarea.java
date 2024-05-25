package com.example.lab5_20182895;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab5_20182895.databinding.ActivityEditarTareaBinding;
import com.example.lab5_20182895.entity.Tarea;

import java.util.Calendar;
import java.util.Date;

public class EditarTarea extends AppCompatActivity {

    private ActivityEditarTareaBinding binding;
    private EditText editTitleEditText;
    private EditText editDescriptionEditText;
    private TextView editSelectedDateTextView;
    private TextView editSelectedTimeTextView;
    private Date selectedDate;
    private Tarea tarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditarTareaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editTitleEditText = binding.editTaskTitle;
        editDescriptionEditText = binding.editTaskDescription;
        editSelectedDateTextView = binding.editSelectedDateText;
        editSelectedTimeTextView = binding.editSelectedTimeText;

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("task")) {
            tarea = (Tarea) intent.getSerializableExtra("task");
            if (tarea != null) {
                editTitleEditText.setText(tarea.getTitulo());
                editDescriptionEditText.setText(tarea.getDescripcion());
                selectedDate = tarea.getFecha();
                if (selectedDate != null) {
                    editSelectedDateTextView.setText(selectedDate.toString());
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(selectedDate);
                    editSelectedTimeTextView.setText(String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE)));
                }
            }
        }

        binding.editSelectDateButton.setOnClickListener(v -> showDatePickerDialog());
        binding.editSelectTimeButton.setOnClickListener(v -> showTimePickerDialog());

        binding.cancelButton.setOnClickListener(v -> finish());
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            selectedDate = calendar.getTime();
            editSelectedDateTextView.setText(selectedDate.toString());
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            selectedDate = calendar.getTime();  // actualizar selectedDate con la hora seleccionada
            editSelectedTimeTextView.setText(String.format("%02d:%02d", hourOfDay, minute));
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

}
