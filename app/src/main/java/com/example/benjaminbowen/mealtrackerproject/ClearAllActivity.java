package com.example.benjaminbowen.mealtrackerproject;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClearAllActivity extends AppCompatActivity {

    Button clearAllButton;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_all);

        clearAllButton = findViewById(R.id.clear_all_button);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "foods")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();


    }

    public void clearAllData(View v){




        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        db.foodDao().deleteAll();

                        Intent intent = new Intent(ClearAllActivity.this, ListAddActivity.class);
                        startActivity(intent);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        builder.setMessage("Once deleted data can not be recovered. Are you sure you want to continue?").setNegativeButton("No", dialogClickListener).setPositiveButton("Yes", dialogClickListener).show();










    }
}
