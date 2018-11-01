package sachin.sdmp.com.app3;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;



public class WebpageFragment extends Fragment {
    private WebView webpageview = null;
    private int currentId = -1;
    private int webpageArrayLength;
    int getShownIndex() {
        return currentId;
    }
    void showWebpageAtIndex(int newIndex) {

        if (newIndex < 0 || newIndex >= webpageArrayLength) {
            return;
        }
        currentId = newIndex;
        // get web settings
        WebSettings webSettings = webpageview.getSettings();
        // enable java script
        webSettings.setJavaScriptEnabled(true);
        // load url
        webpageview.loadUrl(Activity2.webPages[currentId]);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retain this Fragment across Activity reconfigurations
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // The last parameter is false because the returned view does not need to be attached to the container ViewGroup
        return inflater.inflate(R.layout.webpage_fragment, container, false);
    }

    //Set up information about webpageView

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        webpageview = (WebView) getActivity().findViewById(R.id.webpageView);
        webpageArrayLength = Activity2.webPages.length;
        showWebpageAtIndex(currentId);

    }
}
