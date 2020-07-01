package com.example.parcours_mobile_7_gestiondesemployee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class add_employee extends AppCompatActivity {
private TextInputLayout user_name,last_name,phone;
private Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        user_name = findViewById(R.id.add_FN);
        last_name = findViewById(R.id.add_LN);
        phone = findViewById(R.id.add_PHONE);
        btn_add=findViewById(R.id.ADD_BTN);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name_str = user_name.getEditText().getText().toString();
                String last_name_str = last_name.getEditText().getText().toString();
                String phone_str = phone.getEditText().getText().toString();
                if(user_name_str.isEmpty() || last_name_str.isEmpty() ||phone_str.isEmpty()){
                    Toast.makeText(add_employee.this, "complete ....", Toast.LENGTH_SHORT).show();
                }else {
                    addUser(user_name_str,last_name_str,phone_str);
                }
            }
        });

    }

    private void addUser(String user_name_str, String last_name_str, String phone_str) {
        String url ="https://backend-people-crud-app.herokuapp.com/users/add";
        JsonObject user_json_add = new JsonObject();
        user_json_add.addProperty("firstname",user_name_str);
        user_json_add.addProperty("lastname",last_name_str);
        user_json_add.addProperty("phone",phone_str);
        Ion.with(add_employee.this)
                .load(url)
                .setJsonObjectBody(user_json_add)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                       if(e!=null){
                           Toast.makeText(add_employee.this, "EXEPTION :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                       }else {
                           Toast.makeText(add_employee.this, "user added successfully !", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(add_employee.this,MainActivity.class);
                           startActivity(intent);
                       }
                    }
                });
    }
}