package com.thsuterio.barb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class AdaptadorDialogAgendar extends RecyclerView.Adapter<AdaptadorDialogAgendar.ViewHolder> {

    Context context;
    List<ObjServicoEscolha> lista_opcoes, lista_escolhas = new ArrayList<>();

    private listaOpcoes escutarOpcoes;

    public AdaptadorDialogAgendar(Context context, List<ObjServicoEscolha> lista_opcoes, listaOpcoes escutarOpcoes) {
        this.context = context;
        this.lista_opcoes = lista_opcoes;
        this.escutarOpcoes = escutarOpcoes;
    }



    @NonNull
    @Override
    public AdaptadorDialogAgendar.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.modelo_servico_pequeno, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorDialogAgendar.ViewHolder holder, int position) {
        //Formatar valor
        String fmt_valor = "R$ " + ControleDados.getInstance().lista_servico_cd.get(position).getValor_servico();
        //Setando os vlores no modelo
        holder.nome_servico.setText(ControleDados.getInstance().lista_escolha.get(position).getNome_escolha());
        holder.valor_servico.setText(fmt_valor);

        //Verificar click em um item
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Pegar a posição atual do item escolhido
                int pos = holder.getAdapterPosition();

                //Verifica se a posição do item clicado e valida
                if (pos == RecyclerView.NO_POSITION)  return;

                //passando o item selecionado para a dialog atravez da interface
                escutarOpcoes.itemEscolhido(lista_opcoes.get(pos));
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista_opcoes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nome_servico, valor_servico;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome_servico = itemView.findViewById(R.id.txtModeloPequenoNomeServico);
            valor_servico = itemView.findViewById(R.id.txtModeloPequenoValorServico);
        }
    }

    //Interfaces
    public interface listaOpcoes{
        //metodo para acessar o item clicado na tela de dialog para trabalhar o item
        void itemEscolhido(ObjServicoEscolha servico_escolha);
    }

    //Atualizar lista
    public void atualizarListaEscolhas(List<ObjServicoEscolha> lista_atualizada){
        this.lista_opcoes = lista_atualizada;
        notifyDataSetChanged();
    }

}
