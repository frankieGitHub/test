package com.coolfaceid.coolpad;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.support.design.widget.TabLayout;

import com.coolfaceid.coolpad.Fragment.CallsFragment;
import com.coolfaceid.coolpad.Fragment.ChatFragment;
import com.coolfaceid.coolpad.Fragment.ContactsFragment;
import com.coolfaceid.coolpad.Fragment.FaceRecordFragment;
import com.coolfaceid.coolpad.Fragment.FaceUnlockFragment;

public class MainActivity extends AppCompatActivity {

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    //Fragments
    FaceRecordFragment faceRcordFragment;
    FaceUnlockFragment faceUnlockFragment;
    ChatFragment chatFragment;
    CallsFragment callsFragment;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_without_icon);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position,false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);

        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());
    }


    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        callsFragment=new CallsFragment();
        chatFragment=new ChatFragment();
        faceRcordFragment = new FaceRecordFragment();
        faceUnlockFragment = new FaceUnlockFragment();

        adapter.addFragment(faceRcordFragment,"人脸录入");
        adapter.addFragment(faceUnlockFragment,"人脸解锁");
        viewPager.setAdapter(adapter);
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
