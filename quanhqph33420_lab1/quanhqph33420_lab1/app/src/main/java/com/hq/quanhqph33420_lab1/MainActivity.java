package com.hq.quanhqph33420_lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseFirestore db;
    Context context = this;
    String str = "";
    UserModel md = null;
    TextView txt_m;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_m = findViewById(R.id.txt_main);
        recyclerView = findViewById(R.id.rcv_);
        db = FirebaseFirestore.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reload();

        FloatingActionButton add_ = findViewById(R.id.btn_add);
        add_.setOnClickListener(e -> {
            insert();
            reload();
        });
        FloatingActionButton up_ = findViewById(R.id.btn_upp);
        FloatingActionButton del_ = findViewById(R.id.btn_dell);
        up_.setOnClickListener(e -> {
            update("1602b831-0177-4b75-b758-56528b838520");
        });
        del_.setOnClickListener(e -> {
            delete("1602b831-0177-4b75-b758-56528b838520");
        });
    }

    void reload() {
        ArrayList<UserModel> list = select();

        adapter_ adapter = new adapter_(list, this);
        recyclerView.setAdapter(adapter);
    }

    void insert() {
        String id = UUID.randomUUID().toString();
        md = new UserModel(id, "user11", "pass11");
        db.collection("User").document(id).set(md.createHashMap()).addOnSuccessListener(unused -> {
            Toast.makeText(context, "Insert complete", Toast.LENGTH_LONG).show();
        }).addOnFailureListener(e -> {
            Toast.makeText(context, "Insert failed", Toast.LENGTH_LONG).show();
        });
    }

    public void clickUp(String id) {
        update(id);
        reload();
    }

    public void clickDel(String id) {
        delete(id);
        reload();
    }

    void update(String id) {
        md = new UserModel(id, "user1123", "pass244");
        db.collection("User").document(id).update(md.createHashMap())
                .addOnSuccessListener(e -> {
                    Toast.makeText(context, "Update complete", Toast.LENGTH_LONG).show();
                }).addOnFailureListener(e -> {
                    Toast.makeText(context, "Update complete", Toast.LENGTH_LONG).show();
                });
    }

    void delete(String id) {
        db.collection("User").document().delete().addOnSuccessListener(e -> {
            Toast.makeText(context, "Delete complete", Toast.LENGTH_LONG).show();
        }).addOnFailureListener(e -> {
            Toast.makeText(context, "Delete complete", Toast.LENGTH_LONG).show();
        });
    }

    ArrayList<UserModel> select() {
        ArrayList<UserModel> list = new ArrayList<>();
        db.collection("User")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        str = "";
                        for (QueryDocumentSnapshot doc : task.getResult()) {
                            UserModel t = doc.toObject(UserModel.class);
                            str += "id: " + t.getId() + "\n";
                            str += "title: " + t.getUser() + "\n";
                            str += "content: " + t.getPass() + "\n";
                            txt_m.setText(str);
                            list.add(t);
                        }

                    }

                });
        return list;
    }
}