package com.support.sport.sportsupport.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.support.sport.sportsupport.activities.R;
import com.support.sport.sportsupport.models.Member;
import com.support.sport.sportsupport.services.ApiClient;
import com.support.sport.sportsupport.services.ApiInterface;
import com.support.sport.sportsupport.services.CrudOPs;
import com.support.sport.sportsupport.services.PostService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */

public class SignInActivity extends AppCompatActivity  {

    private final String TAG = this.getClass().getSimpleName();
    private UserLoginTask mAuthTask = null;

    List<Member> memberList;
    // UI references.

    private EditText mUsernameView, mPasswordView;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        // Set up the login form.

        mUsernameView= (EditText) findViewById(R.id.login_username);
        mPasswordView = (EditText) findViewById(R.id.login_password);



        signInButton = (Button) findViewById(R.id.userName_sign_in_button);

        signInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = mUsernameView.getText().toString();
                String password = mPasswordView.getText().toString();

                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                CrudOPs cr = new CrudOPs();


                Member temp = cr.readOne(apiService,userName,password);
                if(temp!=null){
                    Toast.makeText(SignInActivity.this,"Hello "+temp.getName()+" :)",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignInActivity.this,MenuActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(SignInActivity.this,"Password or Username invalid!",Toast.LENGTH_LONG).show();
                }


            }
        });
    }



    /*private void getAllPosts(PostService postService, final String username, String password) {
        Call<List<Member>> getAllPostsCall = postService.getAllPosts();
        final boolean checkusername=false;
        boolean checkpassword=false;
        boolean check=false;

        getAllPostsCall.enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {
                //displayPost(response.body().get(0));
                for(int i=0; i<response.body().size();i++){
                    memberList.add(response.body().get(i));

                }

            }

            @Override
            public void onFailure(Call<List<Member>> call, Throwable t) {
                Log.e(TAG, "Error occured while fetching post.");
            }
        });

    }*/


    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }



    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Void... voids) {
            return null;
        }
    }
}

