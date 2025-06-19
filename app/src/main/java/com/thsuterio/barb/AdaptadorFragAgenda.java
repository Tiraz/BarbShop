package com.thsuterio.barb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

public class AdaptadorFragAgenda extends RecyclerView.Adapter<AdaptadorFragAgenda.ViewHolder> {
    Context context;
    List<ObjAgendado> lista_agendado;

    public AdaptadorFragAgenda(Context context, List<ObjAgendado> lista_agendado) {
        this.context = context;
        this.lista_agendado = lista_agendado;
    }

    @NonNull
    @Override
    public AdaptadorFragAgenda.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.modelo_agendado_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorFragAgenda.ViewHolder holder, int position) {

        holder.nome_agenda.setText(lista_agendado.get(position).getNome_agendado());
        holder.valor_agenda.setText(String.valueOf(lista_agendado.get(position).getValor_agendado()));/**Formatar depois**/
        holder.dia_agenda.setText(lista_agendado.get(position).getDia_agendado());
        holder.hora_agenda.setText(lista_agendado.get(position).getHora_agendado());
        //pega imagem aleatoria para compor objeto
        holder.img_agendado.setImageResource(ControleDados.getInstance().avatarRandom());

    }

    @Override
    public int getItemCount() {
        return lista_agendado.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nome_agenda, valor_agenda, dia_agenda,hora_agenda;
        ImageView img_agendado;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome_agenda = itemView.findViewById(R.id.txtModeloAgendaNome);
            valor_agenda = itemView.findViewById((R.id.txtModeloAgendaValor));
            dia_agenda = itemView.findViewById((R.id.txtModeloAgendaDia));
            hora_agenda = itemView.findViewById((R.id.txtModeloAgendaHora));
            img_agendado = itemView.findViewById(R.id.imgModeloAvatarAgenda);
        }
    }

    //Atualizar lista
    public void atualizarListaAgendados(List<ObjAgendado> lista_atualizada){
        lista_agendado = lista_atualizada;
        notifyDataSetChanged();
    }
}
