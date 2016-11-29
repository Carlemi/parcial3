package com.example.carlos.pokeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    Button btn2;
    Button vol;
    ImageView img1;
    ImageView img2;
    TextView ppPc;
    TextView ppJg;
    TextView nom1;
    TextView nom2;
    int ppPC  = 100;
    int ppjg = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "Empieza la Batalla!!", Toast.LENGTH_SHORT);
        btn2 = (Button)findViewById(R.id.btngolpe);
        btn2.setOnClickListener(this);
        vol = (Button)findViewById(R.id.back);
        vol.setOnClickListener(this);
        img1 = (ImageView)findViewById(R.id.imageView);
        img2 = (ImageView)findViewById(R.id.imageView3);
        ppPc = (TextView)findViewById(R.id.pp1);
        ppJg = (TextView)findViewById(R.id.pp2);
        nom1 = (TextView)findViewById(R.id.npc);
        nom2 = (TextView)findViewById(R.id.njg);
        int nume1 = (int) (Math.random() * 721);
        int nume2 = (int) (Math.random() * 721);
        chargedimg(nume1,img1);
        chargedimg(nume2,img2);
        chargedname(nume1,nom1);
        chargedname(nume2, nom2);
        ppPc.setText(String.valueOf(ppPC));
        ppJg.setText(String.valueOf(ppjg));
    }
    public void descargarimg(ImageView imageView,String url){
        ImageLoader mImageLoader;
        mImageLoader = MySingleton.getInstance(this).getImageLoader();
        mImageLoader.get(url, ImageLoader.getImageListener(imageView,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher));

    }
    public void chargedimg(int id, final ImageView imageView){
        MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        String url ="http://pokeapi.co/api/v2/pokemon-form/"+id+"/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i("response",response);
                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            JSONObject jsonObject1=new JSONObject(jsonObject.getString("sprites"));
                            String url=jsonObject1.getString("front_default").toString();
                            descargarimg(imageView,url);
                            Log.i("imagen: ", jsonObject1.getString("front_default").toString());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("","Lo sentimos error!");
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    public void chargedname(int id, final TextView t){

        MySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();
        String url ="http://pokeapi.co/api/v2/pokemon/"+id+"/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("response",response);
                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            Log.i("nombre",jsonObject.getString("name"));
                            t.setText( jsonObject.getString("name"));
                            JSONArray jsonArray=jsonObject.getJSONArray("abilities");
                            for (int i=0;i<jsonArray.length();i++) {

                                Log.i("abilities: ", jsonArray.getJSONObject(i).toString());
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("","¡Error!");
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
    @Override
    public void onClick(View v) {
        int id;
        id = v.getId();
        switch (id)
        {
            case R.id.btngolpe:

                int num1 = (int) (Math.random() * 30);
                int num2 = (int) (Math.random() * 30);

                ppPC= ppPC - num1;
                ppPc.setText(String.valueOf(ppPC));
                ppjg = ppjg - num2;
                ppJg.setText(String.valueOf(ppjg));

                if (Integer.parseInt(ppPc.getText().toString()) <= 0){
                    btn2.setEnabled(false);
                    img1.setImageBitmap(null);
                    nom1.setText("");
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Ganaste", Toast.LENGTH_SHORT);

                    toast1.show();
                }else if(Integer.parseInt(ppJg.getText().toString()) <= 0) {
                    btn2.setEnabled(false);
                    img2.setImageBitmap(null);
                    nom2.setText("");
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Ganó la Máquina", Toast.LENGTH_SHORT);

                    toast1.show();
                }else {

                }

                break;
            case R.id.back:
                Intent act1 = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(act1);
                break;
        }
    }

}

