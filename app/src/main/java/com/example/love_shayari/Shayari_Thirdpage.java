package com.example.love_shayari;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Shayari_Thirdpage extends AppCompatActivity {

    TextView textView;
    ImageView reload,pencil,expand,copy;
    int count=0,i=0;
    GridView gridViewAda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thirdpage_toolspage);
        textView = findViewById(R.id.thirdpagetool_shayaritxt);
        reload = findViewById(R.id.thirdpagetool_Reload);
        pencil = findViewById(R.id.thirdpagetool_Pencil);
        expand = findViewById(R.id.thirdpagetool_Expand);
        copy = findViewById(R.id.thirdpagetool_Copy);

        int position = getIntent().getIntExtra("pos",0);
        String shayari[] = getIntent().getStringArrayExtra("shayari");

        textView.setText(shayari[position]);

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count<15)
                {
                    textView.setBackgroundResource(Configfile.gcarr[i]);
                    i++;
                    count++;
                }else
                {
                    i=0;
                    count=0;
                }
            }
        });

        pencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Shayari_Thirdpage.this, Forthpage.class);
                intent.putExtra("pos",position);
                intent.putExtra("shayari",shayari);
                startActivity(intent);
            }
        });

        expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Shayari_Thirdpage.this);
                bottomSheetDialog.setContentView(R.layout.gridview);
                ExpandAdapter adapter = new ExpandAdapter(Shayari_Thirdpage.this,Configfile.gcarr,shayari[position]);
                gridViewAda=bottomSheetDialog.findViewById(R.id.Expand_Gridview);
                gridViewAda.setAdapter(adapter);
                bottomSheetDialog.show();

                gridViewAda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        textView.setBackgroundResource(Configfile.gcarr[i]);
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}