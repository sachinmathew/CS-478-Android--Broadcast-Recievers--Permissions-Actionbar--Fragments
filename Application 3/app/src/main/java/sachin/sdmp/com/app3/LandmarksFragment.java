package sachin.sdmp.com.app3;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class LandmarksFragment extends ListFragment {

    private ListSelectionListener listener = null;
    private int currentId = -1;

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {

        if (currentId != pos) {
            currentId = pos;

            // Inform Activity2 that an item,position  has been selected
            listener.onListSelection(pos);
        }
        // To indicate the selected item has been checked
        l.setItemChecked(currentId, true);
    }


    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {

                  listener = (ListSelectionListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ListSelectionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {

        super.onActivityCreated(savedState);
        // Set the list adapter for the ListView
        setListAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.landmark_item, Activity2.landmarks));

        // Set  list choice mode to allow only one selection at a time
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        if (-1 != currentId) {
            getListView().setItemChecked(currentId, true);

            // to handle configuration changes
            listener.onListSelection(currentId);
        }
    }


   //call back interface
    public interface ListSelectionListener {
          void onListSelection(int index);
    }

}
