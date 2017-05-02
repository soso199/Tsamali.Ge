package ge.idevelopers.tsamali.tabs;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ge.idevelopers.tsamali.MainActivity;
import ge.idevelopers.tsamali.R;
import ge.idevelopers.tsamali.adapters.OffersAdapter;
import ge.idevelopers.tsamali.models.OffersModel;

/**
 * Created by soso on 4/5/17.
 */

public class Offers extends Fragment {

    public static List<OffersModel>offersList = new ArrayList<>();
    public  RecyclerView recyclerView2;
    public static OffersAdapter offersAdapter;
    public static AlertDialog offersDialog;
    public static boolean success=false;
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


        executeRequest();

        return rootView;
    }

    public void executeRequest()
    {
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
                                String link=blogObject.getString("link");
                                int id=blogObject.getInt("id");

                                OffersModel offersModel= new OffersModel(title, img, text, views,link,id);
                                offersList.add(offersModel);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        success=true;
                        offersAdapter.notifyDataSetChanged();

                        progress.cancel();


                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        String message = null;
                        if (error instanceof NetworkError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (error instanceof ServerError) {
                            message = "The server could not be found. Please try again after some time!!";
                        } else if (error instanceof AuthFailureError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (error instanceof ParseError) {
                            message = "Parsing error! Please try again after some time!!";
                        } else if (error instanceof NoConnectionError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (error instanceof TimeoutError) {
                            message = "Connection TimeOut! Please check your internet connection.";
                        }

                        progress.cancel();

                        offersDialog= new AlertDialog.Builder(getContext())
                                .setTitle("შეცდომა!")
                                .setMessage("გთხოვთ შეამოწმოთ ინტერნეტთსან კავშირი")
                                .setPositiveButton("ხელახლა", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        if(MainActivity.dialog==0) {

                                            if(!Blog.sucsess) {
                                                Button okButton = Blog.dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                                                okButton.performClick();
                                            }
                                            MainActivity.dialog=1;
                                        }
                                        else
                                            MainActivity.dialog=0;

                                        executeRequest();

                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setCancelable(false)
                                .show();
                    }
                }
        );

        RequestQueue queue= Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(getOffers);


        offersAdapter.notifyDataSetChanged();
       // progress.setCanceledOnTouchOutside(false);
        progress.setCancelable(false);
        progress.show();
    }


}
