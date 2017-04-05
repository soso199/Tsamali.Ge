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

/**
 * Created by soso on 4/5/17.
 */

public class BlogsAdapter extends  RecyclerView.Adapter<BlogsAdapter.ViewHolder> {

    Context context;
    private List<BlogsModel> blogItems;




    @Override
    public  ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blogs_adapter, parent, false);


        return new ViewHolder(view);
    }

    public BlogsAdapter(List<BlogsModel> items, Context context) {
        this.blogItems = items;
        this.context = context;
    }


    @Override
    public void onBindViewHolder(BlogsAdapter.ViewHolder holder, int position) {

        BlogsModel model = blogItems.get(position);
        int resID = context.getResources().getIdentifier(model.getImage(), "drawable", context.getPackageName());
        holder.text.setText(model.getText());
        Typeface typeface= Typeface.createFromAsset(context.getAssets(), "fonts/alkroundedmtav-medium.otf");
        holder.text.setTypeface(typeface);
        holder.image.setBackgroundResource(resID);
    }

    @Override
    public int getItemCount() {
        return blogItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        ImageView image;

        // ImageView image_background;
        public ViewHolder(final View itemView) {
            super(itemView);

            text = (TextView) itemView.findViewById(R.id.text);
            image = (ImageView) itemView.findViewById(R.id.image);

        }
    }

    }
