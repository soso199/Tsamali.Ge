package ge.idevelopers.myapplication.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ge.idevelopers.myapplication.R;
import ge.idevelopers.myapplication.models.BlogsModel;
import ge.idevelopers.myapplication.models.OffersModel;

/**
 * Created by soso on 4/5/17.
 */

public class OffersAdapter extends  RecyclerView.Adapter<OffersAdapter.ViewHolder> {

    Context context;
    private List<OffersModel> offerItems;




    @Override
    public  ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_adapter, parent, false);


        return new ViewHolder(view);
    }

    public OffersAdapter(List<OffersModel> items, Context context) {
        this.offerItems = items;
        this.context = context;
    }


    @Override
    public void onBindViewHolder(OffersAdapter.ViewHolder holder, int position) {

       OffersModel model = offerItems.get(position);
        int resID = context.getResources().getIdentifier(model.getImage(), "drawable", context.getPackageName());

        holder.text.setText(model.getText());
        Typeface typeface= Typeface.createFromAsset(context.getAssets(), "fonts/alkroundedmtav-medium.otf");
        holder.text.setTypeface(typeface);
        holder.image.setBackgroundResource(resID);
    }

    @Override
    public int getItemCount() {
        return offerItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        ImageView image;

        // ImageView image_background;
        public ViewHolder(final View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image_offers);
            text = (TextView) itemView.findViewById(R.id.text_offers);

        }
    }

    }
