package com.thsuterio.barb;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuPrincipalActivity extends AppCompatActivity {

    BottomNavigationView bot_nav_tela_principal;
    ViewPager2 view_pag_tela_principal;
    AdaptadorTelaPrincipal adapter_tela_principal;

    AppBarLayout top_bar_layout;
    MaterialToolbar mat_top_bar_config;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu_principal_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mat_top_bar_config  = findViewById(R.id.matTooBarMenuPrincipal);

        mat_top_bar_config.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ConfiguracaoActivity.class));
                finish();
            }
        });


        bot_nav_tela_principal = findViewById(R.id.botNavgationTelaPrincipal);
        view_pag_tela_principal = findViewById(R.id.viewPagTelaPrincipal);

        adapter_tela_principal = new AdaptadorTelaPrincipal(this);
        view_pag_tela_principal.setAdapter(adapter_tela_principal);

        bot_nav_tela_principal.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.itemMenuTalaPrincipalAgenda){
                view_pag_tela_principal.setCurrentItem(0);

            } else if (item.getItemId() == R.id.itemMenuTalaPrincipalServico) {
                view_pag_tela_principal.setCurrentItem(1);

            } else if (item.getItemId() == R.id.itemMenuTalaPrincipalExtrato) {
                view_pag_tela_principal.setCurrentItem(2);

            }


            return true;
        });

        view_pag_tela_principal.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(position == 0){
                    bot_nav_tela_principal.setSelectedItemId(R.id.itemMenuTalaPrincipalAgenda);
                } else if (position == 1) {
                    bot_nav_tela_principal.setSelectedItemId(R.id.itemMenuTalaPrincipalServico);
                } else if (position == 2) {
                    bot_nav_tela_principal.setSelectedItemId(R.id.itemMenuTalaPrincipalExtrato);
                }

            }
        });



    }
}