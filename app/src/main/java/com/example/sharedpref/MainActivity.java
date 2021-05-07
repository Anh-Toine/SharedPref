package com.example.sharedpref;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //public static Greeter greeter;
    private EditText editTextUsr, editTextPass, editTextLastScore;
    private Button buttonSave, buttonLoad;
    private TextView txtViewDetails;
    private LinearLayout linearLayout;
    private int colorID = 0;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    private Intent myIntent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsr=findViewById(R.id.edtTxtUsrName);
        editTextPass=findViewById(R.id.edtTxtPassword);
        editTextLastScore=findViewById(R.id.edtTxtLastScore);

        buttonSave= findViewById(R.id.btnSaveID);
        buttonLoad= findViewById(R.id.btnLoadID);

        buttonLoad.setOnClickListener(MainActivity.this);
        buttonSave.setOnClickListener(MainActivity.this);

        txtViewDetails=findViewById(R.id.txtViewDetails);
        linearLayout = findViewById(R.id.linlayoutID);
        sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menuItemRed){
            linearLayout.setBackgroundColor(
                    getResources().getColor(R.color.colorRed)
            );
            editor.putInt("keyBCol", getResources().getColor(R.color.colorRed));

        }
        if (item.getItemId()==R.id.menuItemGreen){
            linearLayout.setBackgroundColor(
                    getResources().getColor(R.color.colorGreen)
            );
            editor.putInt("keyBCol", getResources().getColor(R.color.colorGreen));

        }
        if (item.getItemId()==R.id.menuItemBlue){
            linearLayout.setBackgroundColor(
                    getResources().getColor(R.color.colorBlue)
            );
            editor.putInt("keyBCol", getResources().getColor(R.color.colorBlue));

        }
        if (item.getItemId()==R.id.menuItemDefault){
            linearLayout.setBackgroundColor(
                    getResources().getColor(R.color.colorDefault)
            );
            editor.putInt("keyBCol", getResources().getColor(R.color.colorDefault));

        }
        editor.commit();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnSaveID){

            String usrName= editTextUsr.getText().toString();
            String passWord= editTextPass.getText().toString();
            String lScore = editTextLastScore.getText().toString();
            int lastScore = Integer.parseInt(lScore);
            showToast(lScore);

            if (usrName.equals("") && passWord.equals("") && lScore.equals("")){
                showToast("Must enter User name, password and last Score");
            }
            else {
                //save to sharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("keyUsr", usrName);
                editor.putString("keyPass", passWord);
                editor.putInt("keyScore", lastScore);
                editor.commit();

                editTextUsr.setText("");
                editTextPass.setText("");
                editTextLastScore.setText("");
                showToast("Data stored successfully");
            }
        }
        else if (v.getId()==R.id.btnLoadID){
            if (sharedPreferences.contains("keyUsr") && sharedPreferences.contains("keyPass")
                    && sharedPreferences.contains("keyScore")) {
                String usrName=sharedPreferences.getString("keyUsr","data not found");
                String passWord=sharedPreferences.getString("keyPass","data not found");
                int scoreValue = sharedPreferences.getInt("keyScore",0);
                /*
                txtViewDetails.setText("Username: " + usrName + "\n" +
                        "Password: "+ passWord + "\n"
                        + "Last Score: " + scoreValue);

                 */
                editTextUsr.setText(usrName);
                editTextPass.setText(passWord);
                editTextLastScore.setText(String.valueOf(scoreValue));
            }
            else{
                txtViewDetails.setText("Error while storing");
            }
        }
    }

    public void showToast(String s1){
        Toast tst;
        tst = Toast.makeText(this,s1,Toast.LENGTH_LONG);
        tst.show();
    }

    protected void onStart( ) {
        super.onStart( );
        showToast("Inside MainActivity:onStart");
        //updateView();

    }

    protected void onRestart( ) {
        super.onRestart( );
        showToast("Inside MainActivity:onReStart");
    }

    protected void onResume( ) {
        super.onResume( );
        showToast("Inside MainActivity:onResume");

    }

    protected void onPause( ) {
        super.onPause( );
        showToast("Inside MainActivity:onPause");

    }

    protected void onStop( ) {
        super.onStop( );
        showToast("Inside MainActivity:onStop");

    }

    protected void onDestroy( ) {
        super.onDestroy( );
        showToast("Inside mainActivity:onDestroy");

    }

}