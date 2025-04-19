package com.thsuterio.barb;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DialogAgendarInputFlagment extends DialogFragment {
    public interface DialogLister{
        void onInputReceived(int codigo, float vl_total);
    }

    DialogLister lister;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Fragment parent_fragment = getParentFragment();
        try {
            lister = (DialogLister) parent_fragment;

        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " deve implementar DialogListern");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_agendar_input_layout,null);

        //Apresentação XML + Java
        EditText edt_nome = view.findViewById(R.id.edtNomeAgendar);
        RecyclerView rec_servicos = view.findViewById(R.id.recServicosAgendar);
        RecyclerView rec_escolhas = view.findViewById(R.id.recServicosEscolhasAgendar);
        List<ObjServicoEscolha> lista_escolhas = new ArrayList();
        Button btn_salvar = view.findViewById(R.id.btnSalvarAgenda);
        Button btn_dia = view.findViewById(R.id.btnDataAgendar);
        Button btn_hora = view.findViewById(R.id.btnHoraAgendar);

        //Instancias
        AdaptadorFragGerServicos adp_servicos = new AdaptadorFragGerServicos(getContext(), ControleDados.getInstance().lista_servico_cd);

        //Ligando RecycleView
        rec_servicos.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rec_servicos.setAdapter(adp_servicos);


        AlertDialog dialog = builder.setView(view).create();

        btn_salvar.setOnClickListener(v -> {
            String input = edt_nome.getText().toString();

            if (lister != null) {
                lister.onInputReceived(123,123);
            } else {
                // Log de erro para depuração
                System.err.println("Erro: lister está null!");
            }

            dialog.dismiss();
        });


        return dialog;
    }
}
