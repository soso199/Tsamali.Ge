package ge.idevelopers.myapplication;

import android.graphics.Color;
import android.graphics.Typeface;
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

import ge.idevelopers.myapplication.tabs.Blog;
import ge.idevelopers.myapplication.tabs.Offers;

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
    private RelativeLayout cover;
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
    public static boolean isSlidable=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        typeface=Typeface.createFromAsset(getAssets(), "fonts/alkroundedmtav-medium.otf");
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        Color color=new Color();
        color.parseColor("#000000");


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabTextColors(R.color.black,R.color.black);
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

        mSlidingPanel = (SlidingPaneLayout) findViewById(R.id.main_content);
        mSlidingPanel.setPanelSlideListener(mPanelListener);
        mSlidingPanel.setParallaxDistance(180);
        mSlidingPanel.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){
            @Override
            public void onSwipeLeft() {
                if(isSlidable)
                    mSlidingPanel.openPane();
                else
                super.onSwipeLeft();
            }

            @Override
            public void onSwipeRight() {
                if(isSlidable)
                    mSlidingPanel.openPane();
                else
                super.onSwipeRight();
            }
        });


        //animation

        humburger_11 = (LinearLayout) findViewById(R.id.humburger_14);
        humburger_21 = (LinearLayout) findViewById(R.id.humburger_24);
        humburger_31 = (LinearLayout) findViewById(R.id.humburger_34);
        humburger_41 = (LinearLayout) findViewById(R.id.humburger_44);
        hamburger_main=(RelativeLayout)findViewById(R.id.humburger_main4);

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
                    open=false;
                }
                else {
                    humburger_41.startAnimation(fadein);
                    humburger_31.startAnimation(fadein);
                    humburger_11.startAnimation(rotateback);
                    humburger_21.startAnimation(rotatetwoback);

                    mSlidingPanel.closePane();
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
            // Show 3 total pages.
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
}
