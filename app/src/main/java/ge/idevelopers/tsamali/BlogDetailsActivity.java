package ge.idevelopers.tsamali;

import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ge.idevelopers.tsamali.adapters.BlogsAdapter;
import ge.idevelopers.tsamali.adapters.OtherBlogsAdapter;
import ge.idevelopers.tsamali.models.BlogsModel;
import ge.idevelopers.tsamali.tabs.Blog;

public class BlogDetailsActivity extends AppCompatActivity {

    private TextView title;
    private TextView text;
    private ImageView image;
    private ImageView fbShare;
    private TextView otherArticlesTet;
    private RecyclerView recyclerView;
    private OtherBlogsAdapter blogsAdapter;
    private List<BlogsModel> threeBlogsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_details);

        Bundle extras = getIntent().getExtras();
            String title_string = extras.getString("title");
            String url = extras.getString("url");
            String text_string = extras.getString("text");
            final String link=extras.getString("link");

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

        text.setText(text_string);
        title.setText(title_string);
        Picasso.with(getApplicationContext()).load(url).into(image);



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
    public void back(View v)
    {
        super.onBackPressed();
    }


}
