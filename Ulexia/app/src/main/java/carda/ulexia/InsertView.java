package carda.ulexia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InsertView extends AppCompatActivity {

    private Button button;
    private TextView tv;
    public static String user_input = "";

//    public static String FILE_NAME = "";
    EditText mEditText;
    EditText mEditName;

//    public List<String> names;
//    public List<String> texts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_view);

        mEditText = findViewById(R.id.edit_text);
        mEditName = findViewById(R.id.edit_name);

        Intent intent = getIntent();
//        names = getIntent().getStringArrayListExtra("names");
//        texts = getIntent().getStringArrayListExtra("texts");
//        button = (Button) findViewById(R.id.button_start);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openCardView();
//            }
//        });
    }

    public void saveUserInput(View v) {
        tv = (TextView) findViewById(R.id.edit_text);

        user_input = tv.getText().toString();
        String text = mEditText.getText().toString();
        String filename = mEditName.getText().toString();
//        FILE_NAME = filename + ".txt";


        // TRY REAL SAVE
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(text.getBytes());
            outputStream.close();
            Toast.makeText(this, "Saved to: " + getFilesDir() + "/" + filename, Toast.LENGTH_LONG).show();

            // instead of save
            // save to names arraylist for intent
//            names.add(filename);
//            texts.add(text);
            // load home view
            Intent intent = new Intent(this, HomeView.class);
//            intent.putStringArrayListExtra("names", (ArrayList<String>) names);
//            intent.putStringArrayListExtra("texts", (ArrayList<String>) texts);
            startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
        // END TRY REAL SAVE


//        // instead of save
//        // save to names arraylist for intent
//        names.add(filename);
//        texts.add(text);
//        // load home view
//        Intent intent = new Intent(this, HomeView.class);
//        intent.putStringArrayListExtra("names", (ArrayList<String>) names);
//        intent.putStringArrayListExtra("texts", (ArrayList<String>) texts);
//        startActivity(intent);

        // added: save user input to a file in homeview
//        FileOutputStream fos = null;
//        try {
//            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
//            fos.write(text.getBytes());
//            tv.getText(); //.clear();
//            Toast.makeText(this, "Saved to: " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
//            // save to block object
////            blocks.add(new Block(filename));
//            // save to names arraylist for intent
//            names.add(filename);
//            // load home view
//            Intent intent = new Intent(this, HomeView.class);
//            intent.putStringArrayListExtra("names", (ArrayList<String>) names);
//            intent.putStringArrayListExtra("texts", (ArrayList<String> texts))
//            startActivity(intent);
//
//        } catch (FileNotFoundException e){
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

    }

//    public void load(View v) {
//        FileInputStream fis = null;
//
//        try {
//            fis = openFileInput(FILE_NAME);
//            InputStreamReader isr = new InputStreamReader(fis);
//            BufferedReader br = new BufferedReader(isr);
//            StringBuilder sb = new StringBuilder();
//            String text;
//
//            while ((text = br.readLine()) != null) {
//                sb.append(text).append("\n");
//            }
//
//            mEditText.setText(sb.toString());
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

//    public String getUserInput() {
//        return user_input;
//    }

//    public void openCardView() {
//        Intent intent = new Intent(this, CardView.class);
////        saveUserInput();
//        startActivity(intent);
//    }
}
