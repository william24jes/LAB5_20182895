package com.example.lab5_20182895.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5_20182895.R;
import com.example.lab5_20182895.entity.Tarea;

import java.util.List;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TaskViewHolder> {

    private List<Tarea> tareaList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(Tarea tarea);
    }

    public TareaAdapter(List<Tarea> tareaList) {
        this.tareaList = tareaList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarea_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Tarea tarea = tareaList.get(position);
        holder.bind(tarea);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && mListener != null) {
                    Tarea tarea = tareaList.get(position);
                    mListener.onItemClick(tarea);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tareaList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        // Declarar las vistas que deseas modificar en el elemento de lista
        private TextView titleTextView;
        private TextView descriptionTextView;
        private TextView dueDateTextView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            // Inicializar las vistas del diseño del elemento de lista
            titleTextView = itemView.findViewById(R.id.recTitulo);
            descriptionTextView = itemView.findViewById(R.id.redDescripción);
            dueDateTextView = itemView.findViewById(R.id.recFecha);
        }

        public void bind(Tarea tarea) {
            // Establecer los valores de las vistas con los datos de la tarea
            titleTextView.setText(tarea.getTitulo());
            descriptionTextView.setText(tarea.getDescripcion());
            dueDateTextView.setText(tarea.getFecha().toString());
        }
    }
}
