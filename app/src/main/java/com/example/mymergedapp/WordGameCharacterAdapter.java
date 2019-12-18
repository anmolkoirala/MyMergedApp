package com.example.mymergedapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WordGameCharacterAdapter extends RecyclerView.Adapter<WordGameCharacterAdapter.GameTextHolder> {

    Context context;
    private Character[] characterList;
    EditText Text;

    public WordGameCharacterAdapter(Context context, Character[] characterList,EditText word) {
        this.context = context;
        this.characterList = characterList;
        this.Text = word;
    }

    @NonNull
    @Override
    public GameTextHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wordgametext, parent,false);
        GameTextHolder charHolder = new GameTextHolder(view);
        return charHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final GameTextHolder holder, final int position) {
        holder.textWord.setText(String.valueOf(characterList[position]));
        holder.textWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String setword = Text.getText().toString() + characterList[position];
                Text.setText(setword);
                holder.textWord.setTextColor(context.getResources().getColor(R.color.colorBlack));
                holder.textWord.setBackgroundColor(context.getResources().getColor(R.color.colorGrey));
                holder.textWord.setOnClickListener(null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return characterList.length;
    }

    public class GameTextHolder extends RecyclerView.ViewHolder{

        LinearLayout linearLayout;
        TextView textWord;

        public GameTextHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            textWord = itemView.findViewById(R.id.txtChar);
        }
    }
}
