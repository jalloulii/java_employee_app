package com.example.parcours_mobile_7_gestiondesemployee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class upadte_delete_user extends AppCompatActivity {
private Button update_btn,remove_btn;
private TextInputLayout firstname,lastname,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upadte_delete_user);
            update_btn=findViewById(R.id.update_btn);
            remove_btn=findViewById(R.id.remove_btn);

            firstname=findViewById(R.id.firstname_input);
            lastname=findViewById(R.id.lastname_input);
            phone=findViewById(R.id.phone_input);

        Intent intent = getIntent();
        final String id_str = intent.getStringExtra("id");
        String firstname_str =intent.getStringExtra("firstname");
        String lastname_str =intent.getStringExtra("lastname");
        final String phone_str = intent.getStringExtra("phone");

        firstname.getEditText().setText(firstname_str, TextView.BufferType.EDITABLE);
        lastname.getEditText().setText(lastname_str,TextView.BufferType.EDITABLE);
        phone.getEditText().setText(phone_str,TextView.BufferType.EDITABLE);

        remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://backend-people-crud-app.herokuapp.com/users/"+id_str;
                Ion.with(upadte_delete_user.this)
                        .load("DELETE",url)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if(e!=null){
                                    Toast.makeText(upadte_delete_user.this, "EXEPTION"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(upadte_delete_user.this, "user deleted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(upadte_delete_user.this,MainActivity.class);
                    startActivity(intent);
                                }
                            }
                        });
            }


        });
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String first_name = firstname.getEditText().getText().toString();
                    String last_name = lastname.getEditText().getText().toString();
                    String phone_up = phone.getEditText().getText().toString();


                String url="https://backend-people-crud-app.herokuapp.com/users/update";
                JsonObject json = new JsonObject();
                json.addProperty("_id",id_str);
                json.addProperty("firstname",first_name);
                json.addProperty("lastname",last_name);
                json.addProperty("phone",phone_up);

                Ion.with(upadte_delete_user.this)
                        .load(url)
                        .setJsonObjectBody(json)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                               if(e!=null){
                                   Toast.makeText(upadte_delete_user.this, "EXEPTIOn"+e.getMessage(), Toast.LENGTH_SHORT).show();
                               }else {
                                   Toast.makeText(upadte_delete_user.this, "user upadted successfully", Toast.LENGTH_SHORT).show();
                                   Intent intent = new Intent(upadte_delete_user.this,MainActivity.class);
                                   startActivity(intent);
                               }
                            }
                        });
            }
        });

    }
}