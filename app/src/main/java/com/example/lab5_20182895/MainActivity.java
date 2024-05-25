package com.example.lab5_20182895;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5_20182895.Adapter.TareaAdapter;
import com.example.lab5_20182895.databinding.ActivityMainBinding;
import com.example.lab5_20182895.entity.Tarea;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<Tarea> tareaList;
    private TareaAdapter tareaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tareaList = new ArrayList<>();
        tareaAdapter = new TareaAdapter(tareaList);

        RecyclerView recyclerView = binding.taskRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(tareaAdapter);

        tareaAdapter.setOnItemClickListener(new TareaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Tarea tarea) {
                // Implementa lo que desees hacer cuando se haga clic en un elemento del RecyclerView
                // Por ejemplo, puedes iniciar la actividad EditTaskActivity pasando la tarea seleccionada
                Intent intent = new Intent(MainActivity.this, EditarTarea.class);
                intent.putExtra("task", tarea);
                startActivity(intent);
            }
        });

        Button addButton = binding.addTaskButton;
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AgragarTarea.class);
                startActivityForResult(intent, 1);
            }
        });

        // Cargar tareas desde el almacenamiento interno
        loadTasks();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Tarea newTarea = (Tarea) data.getSerializableExtra("task");
            tareaList.add(newTarea);
            tareaAdapter.notifyDataSetChanged();
            saveTasks();
        }
    }

    private void saveTasks() {
        // Guardar las tareas en el almacenamiento interno
        try (FileOutputStream fos = openFileOutput("tasks.dat", MODE_PRIVATE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(new ArrayList<>(tareaList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTasks() {
        // Cargar las tareas desde el almacenamiento interno
        try (FileInputStream fis = openFileInput("tasks.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            tareaList.addAll((List<Tarea>) ois.readObject());
            tareaAdapter.notifyDataSetChanged();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
