package carda.ulexia;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;



public class RVAdapter extends RecyclerView.Adapter<RVAdapter.BlockViewHolder> {

    public Context context;

    public static class BlockViewHolder extends RecyclerView.ViewHolder {

        CardView cv;

        Button pButton;
        ImageButton rButton;
//        String pText;
        //
//        TextView filename;
//        TextView filedata;


        BlockViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
//            pText =
            pButton = (Button)itemView.findViewById(R.id.pButton);
            rButton = (ImageButton)itemView.findViewById(R.id.rButton);
        }
    }

    List<Block> blocks;

    RVAdapter(List<Block> blocks, Context context){
        this.blocks = blocks;
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public BlockViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        BlockViewHolder pvh = new BlockViewHolder(v);
        return pvh;
    }


    @Override
    public void onBindViewHolder(BlockViewHolder blockViewHolder, int i) {
        final String name = blocks.get(i).filename;
//        final String text = blocks.get(i).filetext;
        blockViewHolder.pButton.setText(name);


        // block variables

        blockViewHolder.pButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // load file
//                String textcontent = text;
                Log.d("myTag", "pbutton clicked");
                Log.d("myTag", name);
//                Log.d("myTag", text);
                FileInputStream fis = null;

//        FileInputStream fis = null;
//
                try {
                    fis = context.openFileInput(name);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String text;

                    while ((text = br.readLine()) != null) {
                        sb.append(text).append("\n");
                    }
//                    Toast.makeText(this, "Loaded: " + sb.toString(), Toast.LENGTH_LONG).show();

//                    mEditText.setText(sb.toString());
                    Log.d("myTag", "worked");
                    Log.d("myTag", "Filename: " + name);
                    Log.d("myTag", "Sb string: " + sb.toString());

                    Intent intent = new Intent(context, CardaView.class);
                    intent.putExtra("textcontent", sb.toString());
                    Log.d("myTag", "created intent inside try");
                    context.startActivity(intent);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Log.d("myTag", "caught2");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("myTag", "caught3");
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

        blockViewHolder.rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Log.d("myTag", "rbutton clicked");

                try {
                    // remove file and reload homeview activity
                    context.deleteFile(name);

                    Intent intent = new Intent(context, HomeView.class);
                    context.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("myTag", "problem removing file name: " + name);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return blocks.size();
    }


}