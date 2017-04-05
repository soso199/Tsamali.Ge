package ge.idevelopers.myapplication.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import ge.idevelopers.myapplication.R;
import ge.idevelopers.myapplication.adapters.OffersAdapter;
import ge.idevelopers.myapplication.models.OffersModel;

/**
 * Created by soso on 4/5/17.
 */

public class Offers extends Fragment {

    private List<OffersModel>offersList = new ArrayList<>();
    private RecyclerView recyclerView2;
    private OffersAdapter offersAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.offers, container, false);

        recyclerView2 = (RecyclerView) rootView.findViewById(R.id.recycler_view_2);
        offersAdapter = new OffersAdapter(offersList,getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView2.setLayoutManager(mLayoutManager);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(offersAdapter);

        for(int i=0;i<8;i++) {
            OffersModel model = new OffersModel("საქველმოქმედო აქცია..","a4");
            offersList.add(model);
            model = new OffersModel("საქველმოქმედო აქცია..","a5");
            offersList.add(model);
            model = new OffersModel("საქველმოქმედო აქცია..","a6");
            offersList.add(model);
        }

        offersAdapter.notifyDataSetChanged();

        return rootView;
    }
}
