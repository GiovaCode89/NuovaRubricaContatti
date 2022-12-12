package com.example.nuovaRubricaContatti;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Locale;


public class EditContactActivity extends AppCompatActivity {

    private final int SELECT_IMAGE = 5;
    private ImageView imgContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
    }


    @Override
    protected void onResume() {
       imgContact=findViewById(R.id.imageContact);

        //Modifica TextView 'titleText'
        TextView titleText = findViewById(R.id.titleText);
        if (Locale.getDefault().getLanguage().equals("en")){
            titleText.setText("Edit contact");
        }else{
            titleText.setText("Modifica contatto");
        }


        //gestione evento su addImageButton
        setListenerOnAddImageButton();
        setListenerOnOkButton();
        setListenerOnCancelButton();


        super.onResume();
    }


    public void setListenerOnOkButton(){
        View okButton = findViewById(R.id.addButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent();
                setResult(-1, i);
                finish();
            }
        });
    }

    public void setListenerOnCancelButton (){
        View cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setListenerOnAddImageButton(){
        View chooseImageButton = findViewById(R.id.addImageButton);
        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_IMAGE);
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent i) {

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_IMAGE) {
                // Get the url of the image from data
                Uri selectedImageUri = i.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imgContact.setImageURI(selectedImageUri);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, i);
    }
}