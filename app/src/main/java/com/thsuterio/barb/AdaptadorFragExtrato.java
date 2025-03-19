package com.thsuterio.barb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

public class AdaptadorFragExtrato extends RecyclerView.Adapter<AdaptadorFragExtrato.ViewHolder> {

    List<ObjExtrato> lista_extrato;
    Context context;

    public AdaptadorFragExtrato(Context context, List<ObjExtrato> lista_extrato) {
        this.context = context;
        this.lista_extrato = lista_extrato;
    }


    @NonNull
    @Override
    public AdaptadorFragExtrato.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.modelo_extrato_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorFragExtrato.ViewHolder holder, int position) {
        holder.dia_ext.setText(lista_extrato.get(position).getDia_extrato());
        holder.quant_ext.setText(String.valueOf(lista_extrato.get(position).getQuantidade_extrato()));
        holder.valor_ext.setText(String.valueOf(lista_extrato.get(position).getValor_extrato()));
    }

    @Override
    public int getItemCount() {
        return lista_extrato.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dia_ext, quant_ext, valor_ext;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dia_ext = itemView.findViewById(R.id.txtModeloExtratoDate);
            quant_ext = itemView.findViewById(R.id.txtModeloExtratoQuantidade);
            valor_ext = itemView.findViewById(R.id.txtModeloExtratoValor);

        }
    }
}
