package com.example.budget_tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizado.ViewHolder> {
    public void setListadoInformacion(ArrayList<Bolsillo> listadoInformacion) {
        this.listadoInformacion = listadoInformacion;
        notifyDataSetChanged();
    }

    private ArrayList<Bolsillo> listadoInformacion;
    private OnItemClickListener onItemClickListener;

    public AdaptadorPersonalizado(ArrayList<Bolsillo> listadoInformacion){
        this.listadoInformacion = listadoInformacion;
        this.onItemClickListener = null;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    public AdaptadorPersonalizado.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_bolsillos, parent, false);
        return new ViewHolder(miView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersonalizado.ViewHolder holder, int position) {
        Bolsillo miBolsillo = listadoInformacion.get(position);
        holder.enlazar(miBolsillo);
    }

    @Override
    public int getItemCount() {
        return listadoInformacion.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNombre, tvMonto, tvSigla;
        private ImageButton btnEliminar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tv_nombre_bolsillos);
            tvMonto = itemView.findViewById(R.id.tv_monto_bolsillos);
            tvSigla = itemView.findViewById(R.id.tv_sigla_bolsillos);
            btnEliminar = itemView.findViewById(R.id.ibtn_trash_bolsillos);
        }
        public void enlazar(Bolsillo miBolsillo){
            tvNombre.setText(miBolsillo.getNombre());
            tvMonto.setText(miBolsillo.getMonto().toString());
            tvSigla.setText(miBolsillo.getSigla());
            if(onItemClickListener != null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.OnItemClick(miBolsillo, getAdapterPosition());
                    }
                });
                btnEliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.OnItemBtnEliminarClick(miBolsillo, getAdapterPosition());
                    }
                });
            }
        }
    }
    public interface OnItemClickListener{
        void OnItemClick(Bolsillo miBolsillo, int posicion);
        void OnItemBtnEliminarClick(Bolsillo miBolsillo, int posicion);
    }
}