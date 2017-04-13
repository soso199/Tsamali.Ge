package ge.idevelopers.tsamali.tabs;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import ge.idevelopers.tsamali.R;
import ge.idevelopers.tsamali.adapters.OffersAdapter;
import ge.idevelopers.tsamali.models.OffersModel;

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

        //////*****************************\\\\\\\\\\\\\\\\\\\\
        /////// gettinf offers information from api \\\\\\\\\\\

        final String url="http://tsamali.ge/api/action";

        final ProgressDialog progress=new ProgressDialog(getActivity());;
        JsonObjectRequest getOffers=new JsonObjectRequest(
                Request.Method.GET,
                url,
                "",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        JSONArray array = null;
                        try {
                            array = response.getJSONArray("posts");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        offersList.clear();

                        for (int i = 0; i < array.length(); i++) {

                            try {
                                JSONObject blogObject = array.getJSONObject(i);

                                String title = blogObject.getString("title");
                                String img = blogObject.getString("img");
                                String text = blogObject.getString("text");
                                int views = blogObject.getInt("views");

                                OffersModel offersModel= new OffersModel(title, img, text, views);
                                offersList.add(offersModel);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        offersAdapter.notifyDataSetChanged();

                        progress.cancel();


                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        RequestQueue queue= Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(getOffers);


        offersAdapter.notifyDataSetChanged();
        progress.setCanceledOnTouchOutside(false);
        progress.show();
        return rootView;
    }
}
