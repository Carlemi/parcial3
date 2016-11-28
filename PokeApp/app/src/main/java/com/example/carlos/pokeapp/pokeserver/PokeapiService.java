package com.example.carlos.pokeapp.pokeserver;

import com.example.carlos.pokeapp.modelos.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Carlos on 28/11/2016.
 */
//creamos la interface para llamar la lista de los pokemon
public interface PokeapiService {
    @GET("pokemon")
    Call<PokemonRespuesta> obtenerListapokemon();


}
