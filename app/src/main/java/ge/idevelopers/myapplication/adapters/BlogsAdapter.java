package ge.idevelopers.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import ge.idevelopers.myapplication.BlogDetailsActivity;
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
        final String title=model.getTitle();
        final String text=model.getText();
        int seens=model.getViews();
        final String url="http://tsamali.ge/"+model.getImg();

        holder.text.setText(title);
        holder.seens.setText(Integer.toString(seens));

        Typeface typeface= Typeface.createFromAsset(context.getAssets(), "fonts/alkroundedmtav-medium.otf");
        holder.text.setTypeface(typeface);
        Picasso.with(context).load(url).resize(500,300).into(holder.image);

        holder.blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, BlogDetailsActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("url",url);
                intent.putExtra("text",text);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return blogItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        TextView seens;
        ImageView image;
        LinearLayout blog;

        // ImageView image_background;
        public ViewHolder(final View itemView) {
            super(itemView);

            text = (TextView) itemView.findViewById(R.id.text);
            seens=(TextView) itemView.findViewById(R.id.seens);
            image = (ImageView) itemView.findViewById(R.id.image);
            blog=(LinearLayout) itemView.findViewById(R.id.blog);

        }
    }

    }
