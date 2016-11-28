package com.example.carlos.pokeapp.modelos;

/**
 * Created by Carlos on 28/11/2016.
 */
//creamos la clase que traera de la api el nombre y la url de los pokemon
public class Poke {
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setNom(String name) {
        this.name = name;
    }

    private String url;
}
