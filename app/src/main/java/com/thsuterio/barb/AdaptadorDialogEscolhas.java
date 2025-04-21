package com.thsuterio.barb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorDialogEscolhas extends RecyclerView.Adapter<AdaptadorDialogEscolhas.ViewHolder> {

    Context context;
    List<ObjServicoEscolha> lista_escolhas;

    private listaEscolhido escultarEscolha;

    public AdaptadorDialogEscolhas(Context context, List<ObjServicoEscolha> lista_escolhas, listaEscolhido escultarEscolha) {
        this.context = context;
        this.lista_escolhas = lista_escolhas;
        this.escultarEscolha = escultarEscolha;
    }

    @NonNull
    @Override
    public AdaptadorDialogEscolhas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.modelo_servico_pequeno, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorDialogEscolhas.ViewHolder holder, int position) {

        //Formatar valores
        String valor = "R$ " + lista_escolhas.get(position).getValor_escolha();

        //setando os valores
        holder.nome_servico.setText(lista_escolhas.get(position).getNome_escolha());
        holder.valor_servico.setText(valor);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pegar a posição atual do item escolhido
                int pos = holder.getAdapterPosition();

                //Verifica se a posição do item clicado e valida
                if (pos == RecyclerView.NO_POSITION)  return;

                //passando o item selecionado para a dialog atravez da interface
                escultarEscolha.servicoEscolhido(lista_escolhas.get(pos));
            }
        });

    }

    @Override
    public int getItemCount() {
        return lista_escolhas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nome_servico, valor_servico;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome_servico = itemView.findViewById(R.id.txtModeloPequenoNomeServico);
            valor_servico = itemView.findViewById(R.id.txtModeloPequenoValorServico);
        }
    }

    //Interface de acesso
    public interface listaEscolhido{
        void servicoEscolhido(ObjServicoEscolha item_escolhido);
    }

    //Atualizar lista
    public void atualizarListaEscolhas(List<ObjServicoEscolha> lista_atualizada){
        this.lista_escolhas = lista_atualizada;
        notifyDataSetChanged();
    }

}
