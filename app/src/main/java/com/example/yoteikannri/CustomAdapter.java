package com.example.yoteikannri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    //表示するリスト
    private final List<Homework> localDataSet;

    //text_row_item.xmlのtextViewを取得
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    //コンストラクタ
    public CustomAdapter(List<Homework> dataSet) {
        localDataSet = dataSet;
    }

    //text_row_item.xmlを使ってViewHolderを作る
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    //ViewHolderにリストのテキストを与える
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(localDataSet.get(position).getHomework());
    }

    //リストの要素数を返す
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

