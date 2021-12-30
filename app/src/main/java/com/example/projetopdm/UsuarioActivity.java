package com.example.projetopdm;

import static com.example.projetopdm.R.id.profile_item;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UsuarioActivity extends AppCompatActivity {
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        //iniciar na fragment profile
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new ProfileFragment()).commit();

        navigationView = findViewById(R.id.bottomNavigation);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case profile_item:
                        fragment = new ProfileFragment();
                        break;

                    case R.id.agenda:
                        fragment = new AgendamentosFragment();
                        break;

                    case R.id.notificacoes:
                        fragment = new NotificacoesFragment();
                        break;

                    case R.id.info:
                        fragment = new AboutUsFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();
//                navigationView.setSelectedItemId(profile_item); //tava comentado, se der tilt, comenta de novo

                return true;
            }
        });

    }

}