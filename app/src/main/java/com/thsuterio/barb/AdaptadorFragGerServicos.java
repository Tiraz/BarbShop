package com.thsuterio.barb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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
        //Formatando valor
        String fmt_valor = "Valor: R$ " + list_servicos.get(position)
                        .getValor_servico();

        //Setando os valores no modelo
        holder.nome_servico.setText(list_servicos.get(position).getNome_servico());
        holder.valor_servico.setText(fmt_valor);

        //Coletandando o serviço clicado
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pegar a posição atual do item escolhido
                int pos = holder.getAdapterPosition();

                //Verifica se a posição do item clicado e valida
                if ( pos == RecyclerView.NO_POSITION )  return;

                Toast.makeText(context, list_servicos.get(pos).getNome_servico(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list_servicos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nome_servico,tempo_est_servico, valor_servico;

        public ViewHolder (@NonNull View itemView){
            super(itemView);
            nome_servico = itemView.findViewById(R.id.txtModeloNomeServico);
            valor_servico = itemView.findViewById(R.id.txtModeloValorServico);
        }
    }

    // Atualizar lista
    public void atualizarLista (List<ObjServico> listaNova) {

        if ( listaNova != null ) {
            list_servicos = new ArrayList<>(listaNova);
        } else {
            list_servicos = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

}
