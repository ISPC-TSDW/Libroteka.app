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

public class Producto_fauno_Activity extends AppCompatActivity {

    //private ImageView imgInfoPopup;
    private TextView txtvermasFauno;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_producto_fauno);

        txtvermasFauno = findViewById(R.id.txtVerFauno);
        txtvermasFauno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Producto_fauno_Activity.this, Popup_verFauno.class));
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
    public void irPerfil(View view){
        Intent irPerfil = new Intent(this, ProfileActivity.class);
        startActivity(irPerfil);

    }


    //btn agregar a favorito
    /*
    -----------------Backend------------------
    agregar la funcionalidad de agregar a favoritos  @facku @augusto

    */


}