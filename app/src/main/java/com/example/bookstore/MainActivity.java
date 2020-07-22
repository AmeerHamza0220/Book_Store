package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.myrecyclerview);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<Book> books=new ArrayList<>();
        Book book=new Book();
        book.setId(123);
        book.setTitle("Introduction to mathemmatics");
        book.setAuthor("Ameer Hamza");
        book.setEdition("2");
        book.setPrice(100000);
        books.add(book);
        Book book2=new Book();
        book2.setId(1234);
        book2.setTitle("Introduction to Artigicial intellifebd");
        book2.setAuthor("Ameer Hamza");
        book2.setEdition("3");
        book2.setPrice(100000);
        books.add(book2);
        Book book3=new Book();
        book3.setId(1234);
        book3.setTitle("Introduction to Organic Chemistry");
        book3.setAuthor("Ameer Hamza");
        book3.setEdition("7");
        book3.setPrice(1000000);
        books.add(book3);
        adapter=new MyAdapter(this,books);
        recyclerView.setAdapter(adapter);
    }
}
