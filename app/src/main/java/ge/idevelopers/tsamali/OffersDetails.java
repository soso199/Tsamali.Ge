package ge.idevelopers.tsamali;

import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class OffersDetails extends AppCompatActivity {


    private TextView title;
    private TextView text;
    private ImageView image;
    private Button send;
    private Button send2;
    private ImageView fbShare;
    private ImageView cancel;
    private LinearLayout booking;
    private ScrollView scrollView;
    private TextView booking_offer;
    private EditText enter_name;
    private EditText enter_number;
    private EditText enter_date;
    private EditText enter_comment;
    private boolean send_butt;
    private boolean is_form_open=false;
    private  Calendar myCalendar;
    public Tracker mTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_details);

        Bundle extras = getIntent().getExtras();
        String title_string = extras.getString("title");
        String url = extras.getString("url");
        String text_string = extras.getString("text");
        final String link=extras.getString("link");



        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        mTracker.setScreenName(title_string);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());


        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Share")
                .build());


        text = (TextView) findViewById(R.id.main_text);
        title = (TextView) findViewById(R.id.title_text);
        image = (ImageView) findViewById(R.id.details_image);
        booking_offer=(TextView)findViewById(R.id.booking_offer);
        enter_name=(EditText)findViewById(R.id.enter_name);
        enter_number=(EditText)findViewById(R.id.enter_number);
        enter_date=(EditText)findViewById(R.id.enter_date);
        enter_comment=(EditText)findViewById(R.id.enter_comment);

        cancel=(ImageView)findViewById(R.id.cancel);
        send=(Button)findViewById(R.id.send);
        send2=(Button)findViewById(R.id.send2);
        fbShare=(ImageView) findViewById(R.id.fbShare);
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

        text.setText(Html.fromHtml(text_string));
        title.setText(title_string);
        Picasso.with(getApplicationContext()).load(url).into(image);


       myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        enter_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(OffersDetails.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!is_form_open) {
                    AlphaAnimation appear = new AlphaAnimation(0f, 1f);
                    appear.setDuration(1200);
                    appear.setFillAfter(true);
                    booking.setVisibility(View.VISIBLE);
                    booking.setAnimation(appear);

                    is_form_open=true;

                    scrollView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.fullScroll(View.FOCUS_DOWN);
                        }
                    }, 300);
                }
                else
                {


                    String name=enter_name.getText().toString();
                    String number=enter_number.getText().toString();
                    String date=generateDate(enter_date.getText().toString());
                    String comment=enter_comment.getText().toString();


                        try {
                            sendRespond(name,number,date,comment);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                }


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

                is_form_open=false;

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

        fbShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FacebookSdk.sdkInitialize(getApplicationContext());

                ShareLinkContent linkContent;

                ShareDialog shareDialog = new ShareDialog(OffersDetails.this);
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

    private void sendRespond(final String name,final String number,final String date,final String comment) throws JSONException {
        final String URL = "http://tsamali.ge/api/ard/index.php";
// Post params to be sent to the server

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(OffersDetails.this, " მოთხოვნა წარმატებით გაიგზავნა  ", Toast.LENGTH_SHORT).show();
                        cancel.performClick();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String message = "ინტერნეტთან წვდომა ვერ მოხერხდა.. გთხოვთ შეამოწმოთ კავშირი!";
                        if (error instanceof ServerError) {
                            message = "სერვერთან წვდომა ვერ მოხერხდა.. გთხოვთ ცადოთ მოგვიანებით!";
                        }
                        Toast.makeText(OffersDetails.this,message, Toast.LENGTH_SHORT).show();


                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("title", title.getText().toString());
                params.put("name", name);
                params.put("phone", number);
                params.put("date", date);
                params.put("comment", comment);
                params.put("type", "აქციის დაჯავშნა");

                return params;
            }
        };




// add the request object to the queue to be executed
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        enter_date.setText(sdf.format(myCalendar.getTime()));
    }
    public String generateDate(String date)
    {
        String month=date.substring(0,2);

        switch (month)
        {
            case "01":
                month="იანვარი";
                        break;
            case "02":
                month="თებერვალი";
                break;
            case "03":
                month="მარტი";
                break;
            case "04":
                month="აპრილი";
                break;
            case "05":
                month="მაისი";
                break;
            case "06":
                month="ივნისი";
                break;
            case "07":
                month="ივლისი";
                break;
            case "08":
                month="აგვისტო";
                break;
            case "09":
                month="სექტემბერი";
                break;
            case "10":
                month="ოქტომბერი";
                break;
            case "11":
                month="ნოემბერი";
                break;
            case "12":
                month="დეკემბერი";
                break;


        }
        date=date.substring(3,5)+" "+month+" 20"+date.substring(6,8);

        return date;


    }

}
