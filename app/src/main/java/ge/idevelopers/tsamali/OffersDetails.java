package ge.idevelopers.tsamali;

import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class OffersDetails extends AppCompatActivity {


    private TextView title;
    private TextView text;
    private ImageView image;
    private Button send;
    private Button send2;
    private ShareButton fbShare;
    private ImageView cancel;
    private LinearLayout booking;
    private ScrollView scrollView;
    private TextView booking_offer;
    private TextView enter_name;
    private TextView enter_number;
    private TextView enter_date;
    private TextView enter_comment;
    private boolean send_butt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_details);

        Bundle extras = getIntent().getExtras();
        String title_string = extras.getString("title");
        String url = extras.getString("url");
        String text_string = extras.getString("text");
        String link=extras.getString("link");

        text = (TextView) findViewById(R.id.main_text);
        title = (TextView) findViewById(R.id.title_text);
        image = (ImageView) findViewById(R.id.details_image);
        booking_offer=(TextView)findViewById(R.id.booking_offer);
        enter_name=(TextView)findViewById(R.id.enter_name);
        enter_number=(TextView)findViewById(R.id.enter_number);
        enter_date=(TextView)findViewById(R.id.enter_date);
        enter_comment=(TextView)findViewById(R.id.enter_comment);

        cancel=(ImageView)findViewById(R.id.cancel);
        send=(Button)findViewById(R.id.send);
        send2=(Button)findViewById(R.id.send2);
        fbShare=(ShareButton) findViewById(R.id.fbShare);
        booking=(LinearLayout)findViewById(R.id.booking);
        scrollView=(ScrollView)findViewById(R.id.textAreaScroller);



        Typeface forTitles = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/alkroundedmtav-medium.otf");
        Typeface forText = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/bpg_glaho.ttf");
        final InputMethodManager imm= (InputMethodManager) getSystemService(getBaseContext().INPUT_METHOD_SERVICE);

        title.setTypeface(forTitles);
        text.setTypeface(forText);
        send.setTypeface(forTitles);
        send2.setTypeface(forTitles);
        booking_offer.setTypeface(forTitles);
        enter_name.setTypeface(forTitles);
        enter_number.setTypeface(forTitles);
        enter_date.setTypeface(forTitles);
        enter_comment.setTypeface(forTitles);

        text.setText(text_string);
        title.setText(title_string);
        Picasso.with(getApplicationContext()).load(url).into(image);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlphaAnimation appear = new AlphaAnimation(0f, 1f);
                appear.setDuration(1200);
                appear.setFillAfter(true);
                booking.setVisibility(View.VISIBLE);
                booking.setAnimation(appear);

                scrollView.postDelayed(new Runnable() {
                    @Override public void run()
                    {
                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }
                }, 300);


            }
        });
        send2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                send.performClick();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation hide = new AlphaAnimation(1f, 0f);

                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                hide.setDuration(700);
                hide.setFillAfter(true);

                //booking.setAnimation(hide);

                hide.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        booking.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                booking.startAnimation(hide);



            }

        });



        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(link))
                .build();

        fbShare.setShareContent(content);

    }
    public void back(View v)
    {
        super.onBackPressed();
    }

    private void sendRespond()
    {
        final String URL = "/volley/resource/12";
// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", "AbCdEfGh123456");

        JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

// add the request object to the queue to be executed
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(req);
    }
}
