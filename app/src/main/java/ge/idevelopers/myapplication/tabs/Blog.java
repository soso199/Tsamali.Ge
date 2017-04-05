package ge.idevelopers.myapplication.tabs;

/**
 * Created by soso on 4/5/17.
 */
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
import ge.idevelopers.myapplication.adapters.BlogsAdapter;
import ge.idevelopers.myapplication.models.BlogsModel;

public class Blog extends Fragment {

    private List<BlogsModel> blogsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BlogsAdapter blogsAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.blog, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        blogsAdapter = new BlogsAdapter(blogsList,getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(blogsAdapter);



        for(int i=0;i<8;i++) {
            BlogsModel model = new BlogsModel("საკუთარ პრობლემებზე უკეთესია ღმერთთან და არა სხვა ადამიანებთან ლაპარაკი", "a1");
            blogsList.add(model);
            model = new BlogsModel("საკუთარ პრობლემებზე უკეთესია ღმერთთან და არა სხვა ადამიანებთან ლაპარაკი", "a2");
            blogsList.add(model);
            model = new BlogsModel("საკუთარ პრობლემებზე უკეთესია ღმერთთან და არა სხვა ადამიანებთან ლაპარაკი", "a3");
            blogsList.add(model);
        }

        blogsAdapter.notifyDataSetChanged();

        return rootView;
    }
}
