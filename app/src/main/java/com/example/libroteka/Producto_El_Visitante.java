package com.example.libroteka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Producto_El_Visitante extends AppCompatActivity {

    private TextView txtvermasVisitante;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_producto_el_visitante);

        txtvermasVisitante = findViewById(R.id.txtVerJack);
        txtvermasVisitante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Producto_El_Visitante.this, Popup_verVisitante.class));
            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    //btn home
    public void irHome (View view){
        Intent irHome = new Intent(this, Home.class);
        startActivity(irHome);

    }

    //btn ir perfil
    public void irPerfil(View view){
        Intent irPerfil = new Intent(this, ProfileActivity.class);
        startActivity(irPerfil);

    }
}