package com.example.parcours_mobile_7_gestiondesemployee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Adapter extends ArrayAdapter<User> {
private TextView name , email;
Context ctx;
    public Adapter(@NonNull Context context, User[] objects) {
        super(context, R.layout.user_item, objects);
        ctx=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(ctx).inflate(R.layout.user_item,parent,false);

        email = convertView.findViewById(R.id.emailId);
        name = convertView.findViewById(R.id.nameId);

        User user = getItem(position);
        email.setText(user.getEmail());
        name.setText(user.getFirstname()+" "+user.getLastname());
//        yassine yassine@gamil.com ,.......... 0
//        nabil nabil@gamil.com .....1
//        nabil nabil@gamil.com .....2
//        nabil nabil@gamil.com .....3
//        nabil nabil@gamil.com .....4
//        nabil nabil@gamil.com .....5
//        nabil nabil@gamil.com .....6....

//        item 0 => yassine yassine@gamil.com
//        item 1 => nabil nabil@gamil.com....

        return convertView;
    }
}
