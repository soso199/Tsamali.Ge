package ge.idevelopers.myapplication;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BlogDetailsActivity extends AppCompatActivity {

    private TextView title;
    private TextView text;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_details);

        Bundle extras = getIntent().getExtras();
            String title_string = extras.getString("title");
            String url = extras.getString("url");
            String text_string = extras.getString("text");

        text=(TextView)findViewById(R.id.main_text);
        title=(TextView)findViewById(R.id.title_text);
        image=(ImageView)findViewById(R.id.details_image);

        Typeface forTitles= Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/alkroundedmtav-medium.otf");
        Typeface forText= Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/bpg_glaho.ttf");

        title.setTypeface(forTitles);
        text.setTypeface(forText);

        text.setText(text_string);
        title.setText(title_string);
        Picasso.with(getApplicationContext()).load(url).into(image);

    }
}
