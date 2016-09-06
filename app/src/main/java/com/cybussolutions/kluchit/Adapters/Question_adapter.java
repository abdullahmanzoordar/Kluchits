package com.cybussolutions.kluchit.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cybussolutions.kluchit.DataModels.Questions;
import com.cybussolutions.kluchit.R;

import java.util.ArrayList;
import java.util.HashMap;


public class Question_adapter extends ArrayAdapter<Questions> {

    public ArrayList<Questions> list;
    public HashMap<String, String> values = new HashMap<>();
    String q_id[];


    Activity activity;

    public Question_adapter(Context context, int resource, ArrayList<Questions> list, Activity activity) {
        super(context, resource);


        this.list = list;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        View rowView;


        LayoutInflater inflater = activity.getLayoutInflater();
        rowView = inflater.inflate(R.layout.ques_single_row, parent, false);
        TextView txt = (TextView) rowView.findViewById(R.id.q_txt);
        final TextView id = (TextView) rowView.findViewById(R.id.ques_id);


        Questions ques = list.get(position);

        txt.setText(ques.getQ_Txt());

        id.setText(ques.getQ_Id());


        final ImageView positiveimage = (ImageView) rowView.findViewById(R.id.ans_img);
        final ImageView crossimage = (ImageView) rowView.findViewById(R.id.cross_image);


        positiveimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {

                    String questionid = id.getText().toString();
                    positiveimage.setBackgroundResource(R.drawable.thumbsupgreen);
                    String tag = positiveimage.getTag().toString();
                    positiveimage.setTag(1);
                    values.put(questionid, tag);
                   // Toast.makeText(getContext(), values.toString(), Toast.LENGTH_SHORT).show();
                    crossimage.setBackgroundResource(R.drawable.thumbdowngrey1);

                }

            }
        });


        crossimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String questionid = id.getText().toString();
                showdialog(questionid);
                crossimage.setBackgroundResource(R.drawable.thumbdownred);
                crossimage.setTag(0);
                positiveimage.setBackgroundResource(R.drawable.thumbsupgrey2);

            }
        });

        return rowView;
    }


    public HashMap<String, String> getvalue() {
        return values;

    }


    void showdialog(final String position) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.customdialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        final EditText reason = (EditText) dialogView.findViewById(R.id.reason);
        Button submit = (Button) dialogView.findViewById(R.id.submit_reason);

        final AlertDialog b = dialogBuilder.create();
        b.show();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String answer = reason.getText().toString();

                if (answer.equals("") || answer.equals(null)) {
                    values.put(position, "null");
                  //  Toast.makeText(getContext(), values.toString(), Toast.LENGTH_SHORT).show();
                    b.dismiss();
                } else {
                    values.put(position, answer);
                 //   Toast.makeText(getContext(), values.toString(), Toast.LENGTH_SHORT).show();
                    b.dismiss();
                }


            }
        });


    }



}
