package com.example.parcours_mobile_7_gestiondesemployee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView my_list;
    private ArrayList<User> user_array;
    private Adapter my_adapter;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_list = findViewById(R.id.user_list);
        btnAdd=findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,add_employee.class);
                startActivity(intent);
            }
        });

        GetAllUser();


    }

    private void GetAllUser() {
        String url="https://backend-people-crud-app.herokuapp.com/users";
        Ion.with(MainActivity.this)
                .load(url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, final JsonArray result) {
                        if(e != null)
                            Toast.makeText(MainActivity.this, "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        else
                        {
//                            [
//                              {},{},{},{},..........
//                            ]

                            Gson gson = new Gson();
                            final User[] user_tab = gson.fromJson(result.toString(),User[].class);

                            my_adapter = new Adapter(MainActivity.this,user_tab);
                            my_adapter.notifyDataSetChanged(); //refrechi les donner 
                            my_list.setAdapter(my_adapter);

                            my_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                        String id_str = result.get(position).getAsJsonObject().get("_id").getAsString();
                                        String first_name = user_tab[position].getFirstname();
                                        String last_name = user_tab[position].getLastname();
                                        String phone = result.get(position).getAsJsonObject().get("phone").getAsString();
                                        Intent intent = new Intent(MainActivity.this,upadte_delete_user.class);

                                        intent.putExtra("id",id_str);
                                        intent.putExtra("firstname",first_name);
                                        intent.putExtra("lastname",last_name);
                                        intent.putExtra("phone",phone);

                                        startActivity(intent);

                                }
                            });
                        }


                    }
                });

    }
}
