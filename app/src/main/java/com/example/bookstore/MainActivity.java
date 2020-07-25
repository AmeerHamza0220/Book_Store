package com.example.bookstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private BookStoreDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.myrecyclerview);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        database=new BookStoreDatabase(MainActivity.this);
        List<Book> books=getBookList(database);
        adapter=new MyAdapter(this,books);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.newNote:
                buildDialog(database);
        }
        return super.onOptionsItemSelected(item);
    }
    public void buildDialog(final BookStoreDatabase database){
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        final LayoutInflater inflater=this.getLayoutInflater();
        final View dialogView=inflater.inflate(R.layout.edit_book_detail,null);
        int id=database.getRowsCount();
        ((EditText)dialogView.findViewById(R.id.edtid)).setText(String.valueOf(id));
        builder.setView(dialogView)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title=((EditText)dialogView.findViewById(R.id.edttitle)).getText().toString();
                        String author=((EditText)dialogView.findViewById(R.id.edtauthor)).getText().toString();
                        String edition=((EditText)dialogView.findViewById(R.id.edtedition)).getText().toString();
                        String price=((EditText)dialogView.findViewById(R.id.edtprice)).getText().toString();
                        database.SaveData(title,author,edition,Integer.parseInt(price));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      dialog.dismiss();
                    }
                });
        builder.show();

    }
    public List<Book> getBookList(BookStoreDatabase database){
        Cursor cursor=database.ReadData();
        List<Book> bookList=new ArrayList<>();
        Book book=new Book();
        while (cursor.moveToNext()){
            book.setTitle(cursor.getString(1));
            book.setAuthor(cursor.getString(2));
            book.setEdition(cursor.getString(3));
            book.setPrice(Float.parseFloat(cursor.getString(4)));
            bookList.add(book);
        }
        return bookList;
    }
}
