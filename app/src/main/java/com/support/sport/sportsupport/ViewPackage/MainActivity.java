package com.support.sport.sportsupport.ViewPackage;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.UserController;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.Controller.ApiInterface;
import com.support.sport.sportsupport.Controller.ProfileController;

import java.util.List;

import okhttp3.internal.Platform;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    private TextView tvName;
    private TextView tvSurname;
    private TextView tvUsername;
    private TextView tvStatus;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //listView = findViewById(R.id.listview);

        //ProfileController cr = new ProfileController();

        //Member[] members = cr.getAll();
        //getAllPosts(apiService);

        /*if (members != null){
            String[] memberStr = new String[5];
            for(int j=0;j<5;j++){
                memberStr[j]= new String(members[j].getName()+" "+members[j].getSurname()
                        +" "+members[j].getUsername()+" "+members[j].getPassword());
            }
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, members);
            listView.setAdapter(adapter);

        }*/



        //cr.addMember(apiService,0,0,"yeter4","artik","banned","standard","bitsin@gmail.com","nefret","ediyorum");
        //cr.deleteMember(apiService,"yeter4","artik");
        //cr.updateMember(apiService,"priasa","username9","username9","password9","bittimi@gmail.com",null,null);
        //cr.readOne(apiService,"yeter","artik");

        //getAllPosts(postService);
        //createPost(postService, newMember);



    }



}
