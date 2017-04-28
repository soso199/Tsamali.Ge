package ge.idevelopers.tsamali;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import ge.idevelopers.tsamali.adapters.BlogsAdapter;
import ge.idevelopers.tsamali.adapters.OtherBlogsAdapter;
import ge.idevelopers.tsamali.models.BlogsModel;
import ge.idevelopers.tsamali.tabs.Blog;

public class BlogDetailsActivity extends AppCompatActivity {

    private TextView title;
    private TextView text;
    private ImageView image;
    private ImageView fbShare;
    private ImageView imageBack;
    private TextView otherArticlesTet;
    private RecyclerView recyclerView;
    private OtherBlogsAdapter blogsAdapter;
    private List<BlogsModel> threeBlogsList;

    public Tracker mTracker;
    private  Handler mHandler;
    String source;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_details);

        Bundle extras = getIntent().getExtras();
            String title_string = extras.getString("title");
            String url = extras.getString("url");
            final String text_string = extras.getString("text");
            final String link=extras.getString("link");



        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        mTracker.setScreenName(title_string);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());


        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Share")
                .build());

        imageBack=(ImageView) findViewById(R.id.image_cover);
        text=(TextView)findViewById(R.id.main_text);
        title=(TextView)findViewById(R.id.title_text);
        image=(ImageView)findViewById(R.id.details_image);
        fbShare=(ImageView) findViewById(R.id.fbShare_blog);
        otherArticlesTet=(TextView)findViewById(R.id.other_articles_text);

        Typeface forTitles= Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/alkroundedmtav-medium.otf");
        Typeface forText= Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/bpg_glaho.ttf");

        title.setTypeface(forTitles);
        text.setTypeface(forText);
        otherArticlesTet.setTypeface(forTitles);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

       // StrictMode.setThreadPolicy(policy);
        title.setText(title_string);
        Picasso.with(getApplicationContext()).load(url).into(image);


        text.setText(Html.fromHtml(text_string));
        final Spanned[] span = new Spanned[1];
        AsyncTask task = new AsyncTask () {
         @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
             text.setMovementMethod(ScrollingMovementMethod
                     .getInstance());
             if(span != null) {
                 text.setText(span[0]);
             }
}

            @Override
            protected String doInBackground(Object[] params) {
                span[0] =Html.fromHtml(text_string,getImageHTML(),null);
                return null;
            }
        };

        task.execute();




      //  text.setText(Html.fromHtml(text_string,getImageHTML(),null));
        text.setMovementMethod(LinkMovementMethod.getInstance());



//        text.setText(Html.fromHtml(text_string, new Html.ImageGetter() {
//            @Override
//            public Drawable getDrawable(String source) {
//                return null;
//            }
//        }, null));


        /// 3 other article

        threeBlogsList=new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        blogsAdapter = new OtherBlogsAdapter(threeBlogsList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(blogsAdapter);


        int arraySize= Blog.blogsList.size();

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<arraySize; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<3; i++) {
            threeBlogsList.add(Blog.blogsList.get(list.get(i)));
        }



        blogsAdapter.notifyDataSetChanged();

        /////fb shearing
        fbShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FacebookSdk.sdkInitialize(getApplicationContext());

                ShareLinkContent linkContent;

                ShareDialog shareDialog = new ShareDialog(BlogDetailsActivity.this);
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    linkContent = new ShareLinkContent.Builder()
                            .setContentUrl(Uri.parse(link))
                            .build();
                    shareDialog.show(linkContent);
                }



            }
        });

    }
    public Html.ImageGetter getImageHTML() {
        Html.ImageGetter imageGetter = new Html.ImageGetter() {
            public Drawable getDrawable(String source) {
                try {
                    Drawable drawable = Drawable.createFromStream(new URL(source).openStream(), "src name");
                    drawable.setBounds(0, 0, imageBack.getWidth(),imageBack.getHeight());

                    return drawable;
                } catch(IOException exception) {
                    Log.v("IOException",exception.getMessage());
                    return null;
                }
            }
        };
        return imageGetter;
    }

    private Drawable fetch(String urlString) throws MalformedURLException, IOException {
        return new BitmapDrawable(getApplicationContext().getResources(), Picasso.with(getApplicationContext()).load(urlString).get());
    }
    public void back(View v)
    {
        super.onBackPressed();
    }



}
