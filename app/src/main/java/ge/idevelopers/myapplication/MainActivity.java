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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ge.idevelopers.myapplication.tabs.Blog;
import ge.idevelopers.myapplication.tabs.Offers;

public class MainActivity extends AppCompatActivity {

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
        tabLayout.setTabTextColors(R.color.colorPrimaryDark,R.color.colorPrimaryDark);

        appImage = (ImageView) findViewById(android.R.id.home);
        TitleText = (TextView) findViewById(android.R.id.title);

        mSlidingPanel = (SlidingPaneLayout) findViewById(R.id.main_content);
        mSlidingPanel.setPanelSlideListener(mPanelListener);
        mSlidingPanel.setParallaxDistance(180);


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

        }

        @Override
        public void onPanelClosed(View panel) {

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mSlidingPanel.isOpen()) {
                    appImage.animate().rotation(0);
                    mSlidingPanel.closePane();
                    getActionBar().setTitle(getString(R.string.app_name));
                } else {
                    appImage.animate().rotation(90);
                    mSlidingPanel.openPane();
                    getActionBar().setTitle("Menu Titles");
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openOrClosePanel(View v)
    {
        if(open) {
            mSlidingPanel.openPane();
            open=false;
        }
        else {
            mSlidingPanel.closePane();
            open=true;
        }
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
                    ((TextView) tabViewChild).setTextSize(50f);
                }
            }
        }
    }
}
