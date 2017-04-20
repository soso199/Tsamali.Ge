package ge.idevelopers.tsamali;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.SlidingPaneLayout.PanelSlideListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.List;

import ge.idevelopers.tsamali.models.BlogsModel;
import ge.idevelopers.tsamali.tabs.Blog;
import ge.idevelopers.tsamali.tabs.Offers;

public class MainActivity extends AppCompatActivity{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    SlidingPaneLayout mSlidingPanel;
    ListView mMenuList;
    ImageView appImage;
    private TextView TitleText;
    private TextView blog;
    private TextView aqciebi;
    private TextView contact;
    private TextView settings;
    private Boolean open=true;
    private Typeface typeface;
    private TabLayout tabLayout;
    private Animation fadein;
    private Animation rotateback;
    private Animation rotatetwoback;
    private Animation test;
    Animation animation_first;
    Animation animation_two;
    boolean isShow = false;
    private LinearLayout humburger_11;
    private LinearLayout humburger_21;
    private LinearLayout humburger_31;
    private LinearLayout humburger_41;
    private RelativeLayout hamburger_main;
    private RelativeLayout all;
    private RelativeLayout cover;
    public  List<BlogsModel> blogsList;
    public static boolean isSlidable=true;
    public static int dialog=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blog=(TextView)findViewById(R.id.blog_text);
        aqciebi=(TextView)findViewById(R.id.aqciebi_text);
        contact=(TextView)findViewById(R.id.contact_text);
        settings=(TextView)findViewById(R.id.settings_text);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        typeface=Typeface.createFromAsset(getAssets(), "fonts/alkroundedmtav-medium.otf");

        blog.setTypeface(typeface);
        aqciebi.setTypeface(typeface);
        contact.setTypeface(typeface);
        settings.setTypeface(typeface);


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        Color color=new Color();
        color.parseColor("#000000");




        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
       // tabLayout.setTabTextColors(R.color.black,R.color.black);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        isSlidable=true;
                    break;
                    case 1:
                        isSlidable=false;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        appImage = (ImageView) findViewById(android.R.id.home);
        TitleText = (TextView) findViewById(android.R.id.title);

        all=(RelativeLayout)findViewById(R.id.all);
        mSlidingPanel = (SlidingPaneLayout) findViewById(R.id.main_content);
       mSlidingPanel.setPanelSlideListener(mPanelListener);
        mSlidingPanel.setParallaxDistance(180);

        all.setOnTouchListener(new OnSwipeTouchListener(){
            @Override
            public void onSwipeRight() {
                mSlidingPanel.openPane();
                super.onSwipeRight();
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
            }
        }
        );




        //animation

        humburger_11 = (LinearLayout) findViewById(R.id.humburger_14);
        humburger_21 = (LinearLayout) findViewById(R.id.humburger_24);
        humburger_31 = (LinearLayout) findViewById(R.id.humburger_34);
        humburger_41 = (LinearLayout) findViewById(R.id.humburger_44);
        hamburger_main=(RelativeLayout)findViewById(R.id.humburger_main4);
        cover=(RelativeLayout)findViewById(R.id.cover);

        test = AnimationUtils.loadAnimation(this, R.anim.test);
        animation_first = AnimationUtils.loadAnimation(this, R.anim.rotate);
        animation_two = AnimationUtils.loadAnimation(this, R.anim.rotatetwo);
        fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        rotateback = AnimationUtils.loadAnimation(this, R.anim.rotateback);
        rotatetwoback = AnimationUtils.loadAnimation(this, R.anim.rotatetwoback);

        hamburger_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(open) {
                    humburger_41.startAnimation(test);
                    humburger_31.startAnimation(test);
                    humburger_11.startAnimation(animation_first);
                    humburger_21.startAnimation(animation_two);

                    mSlidingPanel.openPane();
                    cover.setVisibility(View.VISIBLE);
                    open=false;
                }
                else {
                    humburger_41.startAnimation(fadein);
                    humburger_31.startAnimation(fadein);
                    humburger_11.startAnimation(rotateback);
                    humburger_21.startAnimation(rotatetwoback);

                    mSlidingPanel.closePane();
                    cover.setVisibility(View.GONE);
                    open=true;
                }
            }
        });




        changeTabsFont();


    }



    PanelSlideListener mPanelListener=new PanelSlideListener() {
        @Override
        public void onPanelSlide(View panel, float slideOffset) {
//            over.setVisibility(View.VISIBLE);
//            arg0.setAlpha(1 - arg1 / 2);
//
        }

        @Override
        public void onPanelOpened(View panel) {

            LinearLayout tabStrip = ((LinearLayout)tabLayout.getChildAt(0));
            tabStrip.setEnabled(false);
            for(int i = 0; i < tabStrip.getChildCount(); i++) {
                tabStrip.getChildAt(i).setClickable(false);
            }
            mViewPager.setActivated(false);
            mViewPager.setClickable(false);
            mViewPager.setEnabled(false);


        }

        @Override
        public void onPanelClosed(View panel) {
            LinearLayout tabStrip = ((LinearLayout)tabLayout.getChildAt(0));
            tabStrip.setEnabled(true);
            for(int i = 0; i < tabStrip.getChildCount(); i++) {
                tabStrip.getChildAt(i).setClickable(true);
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {


        }
        return super.onOptionsItemSelected(item);
    }




    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position)
            {
                case 0:
                     Blog blog =new Blog();
                    return blog;


                case 1:
                    Offers offers =new Offers();
                    return offers;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "ბლოგი";
                case 1:
                    return "აქციები";
            }
            return null;
        }

    }

    private void changeTabsFont() {

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(typeface);
                }
            }
        }
    }
   public void onClick(View v)
    {


    }
    ///sending mail
    public void sendMail(View v)
    {
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setType("message/rfc822");
        i.setData(Uri.parse("mailto:"));
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"tukhashvilisoso@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "testing mail");
        i.putExtra(Intent.EXTRA_TEXT   , "body of email");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(v.getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    ////*****Shearing to facebook****\\\\\\\\\\\\\
    public void fbShare(View v)
    {


        FacebookSdk.sdkInitialize(getApplicationContext());

        ShareLinkContent linkContent;

        ShareDialog shareDialog = new ShareDialog(this);
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("http://tsamali.ge/aqcia/sarelaqsacio-samkurnalo-an-sxeulis-sakoreqcio-masazhi"))
                    .build();
            shareDialog.show(linkContent);
        }


    }
    public void offers(View v)
    {
        hamburger_main.performClick();
        mViewPager.setCurrentItem(1,true);
    }

    public void blogs(View v)
    {
        hamburger_main.performClick();
        mViewPager.setCurrentItem(0,true);
    }





}
