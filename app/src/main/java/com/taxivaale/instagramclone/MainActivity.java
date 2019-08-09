package com.taxivaale.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

   private EditText name;
   private EditText kickSpeed;
   private EditText punchSpeed;
   private Button btnSave;
   private Button btnGetAllData;
   private TextView txtGetData;
   private String allKickBoxers;
   private Button btntransition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(MainActivity.this);
        name =findViewById(R.id.etName);
        kickSpeed = findViewById(R.id.etKickSpeed);
        punchSpeed = findViewById(R.id.etPunchSpeed);
        btnGetAllData = findViewById(R.id.btnGetAllData);
        btntransition = findViewById(R.id.btnNextActivity);
        txtGetData = findViewById(R.id.txtGetData);
        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("5UaySPGsDU", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if (object != null && e == null){

                            txtGetData.setText(object.get("Name")+ "-" + "Punch Speed is " + object.get("Punch_Speed") + " And his kick Speed is " +object.get("Kick_Speed"));

                        }

                    }
                });
            }
        });

        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                queryAll.whereGreaterThanOrEqualTo("Punch_Speed", 150);
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if (e == null){

                            for (ParseObject Kickboxer : objects){
                                allKickBoxers = allKickBoxers + Kickboxer.get("Name") + "\n";
                            }
                            if (objects.size() > 0 ){

                                FancyToast.makeText(MainActivity.this, allKickBoxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            }
                            else{
                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            }

                        }

                    }
                });
            }
        });

        btntransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SignupLoginActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {
        try {
            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("Name", name.getText().toString());
            kickBoxer.put("Punch_Speed", Integer.parseInt(punchSpeed.getText().toString()));
            kickBoxer.put("Kick_Speed", Integer.parseInt(kickSpeed.getText().toString()));
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(MainActivity.this, kickBoxer.get("Name") + " is saved successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                    }
                }
            });
        }
        catch (Exception e){
            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

        }
    }
}
