package ge.idevelopers.tsamali.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ge.idevelopers.tsamali.BlogDetailsActivity;
import ge.idevelopers.tsamali.OffersDetails;
import ge.idevelopers.tsamali.R;
import ge.idevelopers.tsamali.models.OffersModel;

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
        final String title=model.getTitle();
        final String text=model.getText();
        final String url="http://tsamali.ge/"+model.getImg();
        final String link=model.getLink();
        holder.text.setText(title);
        Typeface typeface= Typeface.createFromAsset(context.getAssets(), "fonts/alkroundedmtav-medium.otf");
        holder.text.setTypeface(typeface);

        Picasso.with(context).load("http://tsamali.ge/"+model.getImg()).into(holder.image);

        holder.offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, OffersDetails.class);
                intent.putExtra("title",title);
                intent.putExtra("url",url);
                intent.putExtra("text",text);
                intent.putExtra("link",link);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return offerItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        ImageView image;
        LinearLayout offers;

        // ImageView image_background;
        public ViewHolder(final View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image_offers);
            text = (TextView) itemView.findViewById(R.id.text_offers);
            offers=(LinearLayout) itemView.findViewById(R.id.offers);

        }
    }

    }
