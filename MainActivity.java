package br.com.mjasistemas.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOK = (Button) findViewById(R.id.main_btn_ok);
        EditText edtNome = (EditText) findViewById(R.id.main_edt_nome);
        EditText edtEndereco = (EditText) findViewById(R.id.main_edt_endereco);
        EditText edtCnpj = (EditText) findViewById(R.id.main_edt_cnpj);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solicitarEstabelecimento();
            }
        });

    }

    private void solicitarEstabelecimento() {
        String path = "http://10.0.63.189:8580/ConstrucaowS/rest/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Serviço WS OK", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError) {
                    Toast.makeText(getApplicationContext(), "O servidor demorou para responder", Toast.LENGTH_LONG).show();
                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(), "Não há conexão com o servidor", Toast.LENGTH_LONG).show();
                }
            }
        });

        VolleyContext.getInstance(this).addToRequestQueue(stringRequest);

    }
}
