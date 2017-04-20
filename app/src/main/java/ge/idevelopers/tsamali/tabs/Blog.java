package ge.idevelopers.tsamali.tabs;

/**
 * Created by soso on 4/5/17.
 */
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.NetworkRequest;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Network;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ResponseDelivery;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ge.idevelopers.tsamali.MainActivity;
import ge.idevelopers.tsamali.R;
import ge.idevelopers.tsamali.adapters.BlogsAdapter;
import ge.idevelopers.tsamali.models.BlogsModel;

public class Blog extends Fragment {

    public static List<BlogsModel> blogsList=new ArrayList<>();
    private RecyclerView recyclerView;
    public static BlogsAdapter blogsAdapter;
    private boolean is_taken=false;
    public static AlertDialog dialog;
    public static boolean sucsess=false;

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

        executeRequest();

        return rootView;
    }
    public  void executeRequest()
    {
        final String url="http://tsamali.ge/api/blog";
        final ProgressDialog progress=new ProgressDialog(getActivity());
        final JsonObjectRequest getBlogs=new JsonObjectRequest(
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
                        blogsList.clear();

                        for (int i = 0; i < array.length(); i++) {

                            try {
                                JSONObject blogObject = array.getJSONObject(i);

                                String title = blogObject.getString("title");
                                String img = blogObject.getString("img");
                                String text = blogObject.getString("text");
                                int views = blogObject.getInt("views");
                                String link=blogObject.getString("link");

                                BlogsModel blogsModel = new BlogsModel(title, img, text, views,link);
                                blogsList.add(blogsModel);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Collections.reverse(blogsList);
                        sucsess=true;
                        blogsAdapter.notifyDataSetChanged();
                        progress.cancel();

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
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

                        dialog =new AlertDialog.Builder(getContext())
                                .setTitle("შეცდომა!")
                                .setMessage("გთხოვთ შეამოწმოთ ინტერნეტთსან კავშირი")
                                .setPositiveButton("ხელახლა", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        if(MainActivity.dialog==0) {

                                            if(!Offers.success) {
                                                Button okButton = Offers.offersDialog.getButton(DialogInterface.BUTTON_POSITIVE);
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
        queue.add(getBlogs);

      //  progress.setCanceledOnTouchOutside(false);
        progress.setCancelable(false);
        progress.show();

        blogsAdapter.notifyDataSetChanged();


    }



}
