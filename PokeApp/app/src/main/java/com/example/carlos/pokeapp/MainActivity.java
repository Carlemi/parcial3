package com.example.carlos.pokeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.carlos.pokeapp.modelos.Poke;
import com.example.carlos.pokeapp.modelos.PokemonRespuesta;
import com.example.carlos.pokeapp.pokeserver.PokeapiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //instanciamos una constante

    private static final String TAG = "LISTA";
    //esta variable donde instanciamos la dependencia para hacer los gets desde la api
    private Retrofit rtf;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //la instancia para convertir los datos en Json
        rtf = new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/").addConverterFactory(GsonConverterFactory.create()).build();
        obtenerdatos();
    }
    //tenemos el metodo para obtener los datos del servidor en este caso de la pokeapi
    private void obtenerdatos(){
        PokeapiService srv = rtf.create(PokeapiService.class);
        Call<PokemonRespuesta> PokemonRespuestaCall = srv.obtenerListapokemon();
        PokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override   //este metodo para revisar en consola que nos esté enviando los datos del servidor
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                if (response.isSuccessful()){
                        PokemonRespuesta pkr = response.body();
                        ArrayList<Poke> listapokemon = pkr.getResults();
                    for (int i = 0; i<listapokemon.size();i++){  //un for para recorrer el arraylist 
                        Poke pk = listapokemon.get(i);
                        Log.i(TAG, "Pokemon: " + pk.getName());
                    }
                }else{
                    Log.e(TAG, "en respuesta: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {

                Log.e(TAG, "ha fallado" +t.getMessage());
            }
        });
    }
    public void call(){
        btn = (Button)findViewById(R.id.btnjogo);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id;
        id = view.getId();
        switch (id){
            case R.id.btnjogo:
                Intent act2 = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(act2);
        }
    }
}
