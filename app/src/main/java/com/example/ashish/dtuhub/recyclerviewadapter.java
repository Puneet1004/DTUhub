package com.example.ashish.dtuhub;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class recyclerviewadapter extends RecyclerView.Adapter<recyclerviewadapter.viewholder> {

    Context context;
    RecyclerView recyclerView;
    ArrayList<String> urls = new ArrayList<>();
    ArrayList<String> items = new ArrayList<>();

    public recyclerviewadapter(Context context, RecyclerView recyclerView, ArrayList<String> urls, ArrayList<String> items) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.urls = urls;
        this.items = items;
    }

    public void update(String name, String url) {
        items.add(name);
        urls.add(url);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pdfrecyclerlayout, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.filename.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView filename;

        public viewholder(final View itemView) {
            super(itemView);
            filename = itemView.findViewById(R.id.filename);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = recyclerView.getChildLayoutPosition(v);
                    Intent intent = new Intent();
                    intent.setType(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urls.get(position)));
                    context.startActivity(intent);
                }
            });
        }
    }
}
