package com.example.bookstore;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Book> books;
    private LayoutInflater inflater;

    public MyAdapter(Context context,List<Book> books) {
        this.books = books;
        inflater=LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.recycler_view,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book book=books.get(position);
        holder.txtid.setText("ID:"+book.getId());
        holder.txttitle.setText("Title:"+book.getTitle());
        holder.txtprice.setText("price:"+book.getPrice());
        holder.txtauthor.setText("Author:"+book.getAuthor());
        holder.txtedition.setText("Edition:"+book.getEdition());
       }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtid,txttitle,txtprice,txtedition,txtauthor;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtid=itemView.findViewById(R.id.txtID);
            txtauthor=itemView.findViewById(R.id.txtAuthor);
            txtedition=itemView.findViewById(R.id.txtEdition);
            txttitle=itemView.findViewById(R.id.txtTitle);
            txtprice=itemView.findViewById(R.id.txtPrice);
    }
}

}
