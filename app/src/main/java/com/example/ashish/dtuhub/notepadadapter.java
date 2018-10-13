package com.example.ashish.dtuhub;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ashish.dtuhub.model.notepadlayoutdetails;

import java.util.ArrayList;

public class notepadadapter extends RecyclerView.Adapter<notepadadapter.noteholder> {
    private ArrayList<notepadlayoutdetails> notes;
    private Context context;

    public notepadadapter(Context context, ArrayList<notepadlayoutdetails> notes) {
        this.context = context;
        this.notes = notes;
    }

    @Override
    public void onBindViewHolder(@NonNull noteholder holder, int position) {
        notepadlayoutdetails note = getnote(position);
        if (note != null) {
            holder.notetext.setText(note.getNotetext());
            holder.notedate.setText(noteutils.dateFromlong(note.getNotedate()));
        }

    }

    @NonNull
    @Override
    public noteholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.notepadlayout, parent, false);
        return new noteholder(v);
    }

    @Override
    public int getItemCount() {

        return notes.size();
    }

    private notepadlayoutdetails getnote(int position) {
        return notes.get(position);
    }

    public class noteholder extends RecyclerView.ViewHolder {
        TextView notetext, notedate;

        public noteholder(View itemView) {
            super(itemView);
            notetext = itemView.findViewById(R.id.notetext);
            notedate = itemView.findViewById(R.id.notedate);
        }
    }
}
