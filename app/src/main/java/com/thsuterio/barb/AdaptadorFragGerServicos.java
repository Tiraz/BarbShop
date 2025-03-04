package com.thsuterio.barb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorFragGerServicos extends RecyclerView.Adapter<AdaptadorFragGerServicos.ViewHolder>{

    private Context context;
    private List<ObjServico> list_servicos;


    public AdaptadorFragGerServicos(Context context, List<ObjServico> list_servicos) {
        this.context = context;
        this.list_servicos = list_servicos;
    }

    @NonNull
    @Override
    public AdaptadorFragGerServicos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.modelo_servico_layout, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorFragGerServicos.ViewHolder holder, int position) {
        holder.nome_servico.setText(list_servicos.get(position).getNome_servico());
        holder.tempo_est_servico.setText(String.valueOf(list_servicos.get(position).getTempo_estimado_servico()));
        holder.valor_servico.setText(String.valueOf(list_servicos.get(position).getValor_servico()));
    }

    @Override
    public int getItemCount() {
        return list_servicos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nome_servico,tempo_est_servico, valor_servico;

        public ViewHolder (@NonNull View itemView){
            super(itemView);
            nome_servico = itemView.findViewById(R.id.txtNomeServico);
            tempo_est_servico = itemView.findViewById(R.id.txtTempoEstServico);
            valor_servico = itemView.findViewById(R.id.txtValorServico);
        }
    }
}
