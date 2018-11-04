package sachin.sdmp.com.app3;

/*
  Developed by Sachin Mathew
  This the Activity2 class of Application2 which displays San Fransisco landmarks.
 */

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.LinearLayout;
import android.os.Bundle;
import android.content.res.Configuration;
import android.widget.FrameLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class Activity2 extends AppCompatActivity
        implements LandmarksFragment.ListSelectionListener {

    // landmarks is the array contains the names of landmarks
    public static String[] landmarks;
    // webpage is the array which  contains the homepage links of landmarks
    public static String[] webPages;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;

    private WebpageFragment webpageFragment;
    private LandmarksFragment landmarksFragment;
    private FragmentManager fragmentManager;
    private FrameLayout landmarksFrameLayout, webpageFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the string arrays with the names and urls
        landmarks = getResources().getStringArray(R.array.landmarks);
        webPages = getResources().getStringArray(R.array.webpages);

        //Setting view and fragments
        setContentView(R.layout.activity2);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(myToolbar);

        landmarksFrameLayout = (FrameLayout) findViewById(R.id.landmarks_framelayout);
        webpageFrameLayout = (FrameLayout) findViewById(R.id.webpage_framelayout);
        fragmentManager = getFragmentManager();

        // Start a new FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        landmarksFragment = (LandmarksFragment) fragmentManager.findFragmentById(R.id.landmarks_framelayout);
        if (landmarksFragment == null) {
            landmarksFragment = new LandmarksFragment();
        }
        fragmentTransaction.replace(R.id.landmarks_framelayout,
                landmarksFragment);
        fragmentTransaction.commit();
        // Add  OnBackStackChangedListener to reset the layout for back stack changes
        fragmentManager
                .addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        configureLayout();
                    }
                });

        webpageFragment = (WebpageFragment) fragmentManager.findFragmentById(R.id.webpage_framelayout);
        if (webpageFragment == null) {
            webpageFragment = new WebpageFragment();
        }
        configureLayout();
    }


     // sets the layout depending on the orientation

    private void configureLayout() {
        // for portrait mode

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

             if (!webpageFragment.isAdded()) {
                 // set dimensions of  Landmarks FrameLayout to cover the whole container
                landmarksFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT));
                // set dimensions of Webpage FrameLayout so that its hidden
                webpageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));
            } else {
                landmarksFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));
                 webpageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                        MATCH_PARENT));
            }
        }
        // for landscape mode
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

                if (!webpageFragment.isAdded()) {
                    landmarksFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT));
                    webpageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));
            } else {

                    landmarksFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));
                    webpageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));
            }
        }
    }

    //when user selects an item

    @Override
    public void onListSelection(int index) {

        // If the WebpageFragment has not been added, add it now
        if (!webpageFragment.isAdded()) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.webpage_framelayout, webpageFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                fragmentManager.executePendingTransactions();
        }

        // check if same index is displayed currently
        if (webpageFragment.getShownIndex() != index) {
            // displays the webpage at index
            webpageFragment.showWebpageAtIndex(index);

        }
    }

    // when back button is pressed, set the layout for LandmarksFragment

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // get fragment transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // replace LandmarkFragment with new instance with no item selected
        fragmentTransaction.replace(R.id.landmarks_framelayout, new LandmarksFragment());
        // commit transaction
        fragmentTransaction.commit();
        // execute immediately
        fragmentManager.executePendingTransactions();

    }
}

