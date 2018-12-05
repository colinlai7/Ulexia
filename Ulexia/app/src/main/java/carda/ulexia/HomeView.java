package carda.ulexia;


import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;


import com.tomer.fadingtextview.FadingTextView;

// import file name
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomeView extends AppCompatActivity {

    private FloatingActionButton button;

    // recycler view
    public List<Block> blocks;
//    public List<String> names;
//    public List<String> texts;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view);


        rv=(RecyclerView)findViewById(R.id.rev);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        // initialize fading text view properties
        FadingTextView fadingTextView=(FadingTextView)findViewById(R.id.fade);
        fadingTextView.setTimeout(1, FadingTextView.SECONDS);


//        Intent intent = getIntent();
//        if(intent.getStringArrayListExtra("names") != null) {
//            //Do first time stuff here
//            names = getIntent().getStringArrayListExtra("names");
//        }
//        if(intent.getStringArrayListExtra("texts") != null) {
//            //Do first time stuff here
//            texts = getIntent().getStringArrayListExtra("texts");
//        }


        // check if names array list is empty, should only make new if starting up, no intent
//        if (names == null) {
//            names = new ArrayList<String>();
//        }
//        if (texts == null) {
//            texts = new ArrayList<String>();
//        }
        if (blocks == null){
            blocks = new ArrayList<Block>();
        }

//        names = new ArrayList<String>();
//        names.add("home");
//        blocks = new ArrayList<Block>();
//        for (int i = 0; i < names.size(); i ++){
//            blocks.add(new Block(names.get(i), texts.get(i)));
//        }
//        for (String n: names){
//            blocks.add(new Block(n));
//        }
        // load all files into the blocks
        File dir = getFilesDir();
        File[] flist = dir.listFiles();
        Log.d("myTag", Integer.toString(flist.length));
        for (File f : flist){
            Log.d("myTag", f.toString());
            blocks.add(new Block(f.getName()));
        }
        // initialize adapter after creating file objects
        initializeAdapter();

        button = (FloatingActionButton) findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInsertView();
            }
        });

    }

    public void openInsertView() {

        Intent intent = new Intent(this, InsertView.class);
//        intent.putStringArrayListExtra("names", (ArrayList<String>) names);
//        intent.putStringArrayListExtra("texts", (ArrayList<String>) texts);
        startActivity(intent);
    }

    public void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(blocks, this);
        rv.setAdapter(adapter);
    }





}
