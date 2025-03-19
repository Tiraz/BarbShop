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

public class DialogAgendarInputFlagment extends DialogFragment {
    public interface DialogLister{
        void onInputReceived(String Input);
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

        EditText edt_nome = view.findViewById(R.id.edtNomeAgendar);
        Button btn_salvar = view.findViewById(R.id.btnSalvarAgenda);

        AlertDialog dialog = builder.setView(view).create();

        btn_salvar.setOnClickListener(v -> {
            String input = edt_nome.getText().toString();

            if (lister != null) {
                lister.onInputReceived(input);
            } else {
                // Log de erro para depuração
                System.err.println("Erro: lister está null!");
            }

            dialog.dismiss();
        });


        return dialog;
    }
}
