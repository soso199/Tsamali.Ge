package ge.idevelopers.tsamali;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import ge.idevelopers.tsamali.models.BlogsModel;

public class SplashScreen extends AppCompatActivity {

    public  List<BlogsModel>blogsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        //getPosts();

        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }


}
