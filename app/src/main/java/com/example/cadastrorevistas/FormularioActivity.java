package com.example.cadastrorevistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etCategoria;
    private Spinner spAno;
    private Button btnSalvar;
    private String acao;
    private Revista revista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById( R.id.etNome );
        etCategoria = findViewById( R.id.etCategoria );
        spAno = findViewById( R.id.spAno );
        btnSalvar = findViewById( R.id.btnSalvar );

        acao = getIntent().getStringExtra("acao");
        if( acao.equals("editar")){
            carregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

    }

    private void carregarFormulario(){
        int idRevista = getIntent().getIntExtra("idRevista", 0);
        if( idRevista != 0) {
            revista = RevistaDAO.getRevistaById(this, idRevista);

            etNome.setText( revista.nome );
            etCategoria.setText( revista.categoria );


            String[] arrayAno = getResources().getStringArray(R.array.arrayAno);
            for(int i = 1; i < arrayAno.length ; i++){
                if( Integer.valueOf( arrayAno[i] ) == revista.getAno()){
                    spAno.setSelection( i );
                }
            }
        }
    }

    private void salvar(){
        if( spAno.getSelectedItemPosition() == 0 || etNome.getText().toString().isEmpty() ) {

            Toast.makeText(this, "Todos campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();

        }else{

            if (acao.equals("novo")) {
                revista = new Revista();
            }

            revista.nome = etNome.getText().toString();
            revista.categoria = etCategoria.getText().toString();
            revista.setAno( Integer.valueOf( spAno.getSelectedItem().toString()  ) );

            if( acao.equals("editar")){
                RevistaDAO.editar(revista, this);
                finish();
            }else {
                RevistaDAO.inserir(revista, this);
                etNome.setText("");
                etCategoria.setText("");
                spAno.setSelection(0);
            }
        }
    }



}