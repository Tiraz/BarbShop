package com.thsuterio.barb;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AdaptadorTelaPrincipal extends FragmentStateAdapter {


    public AdaptadorTelaPrincipal(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
               return new FrAgendaFragment();
            case 1:
                return new FrServicosFragment();
            case 2:
                return new FrExtratoFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
