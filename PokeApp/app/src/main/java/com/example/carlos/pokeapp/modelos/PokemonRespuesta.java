package com.example.carlos.pokeapp.modelos;

import java.util.ArrayList;

/**
 * Created by Carlos on 28/11/2016.
 */
//creamos a clase donde obtendremos la respuesta por medio de un arraylist
public class PokemonRespuesta {
    public ArrayList<Poke> getResults() {
        return results;
    }

    public void setResults(ArrayList<Poke> results) {
        this.results = results;
    }

    private ArrayList<Poke> results;
}
