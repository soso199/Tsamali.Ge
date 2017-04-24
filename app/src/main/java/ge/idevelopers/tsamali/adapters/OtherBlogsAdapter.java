package ge.idevelopers.tsamali.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ge.idevelopers.tsamali.BlogDetailsActivity;
import ge.idevelopers.tsamali.R;
import ge.idevelopers.tsamali.models.BlogsModel;

/**
 * Created by soso on 4/5/17.
 */

public class OtherBlogsAdapter extends  RecyclerView.Adapter<OtherBlogsAdapter.ViewHolder> {

    Context context;
    private List<BlogsModel> blogItems;




    @Override
    public  ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.other_blogs_adapter, parent, false);


        return new ViewHolder(view);
    }

    public OtherBlogsAdapter(List<BlogsModel> items, Context context) {
        this.blogItems = items;
        this.context = context;
    }


    @Override
    public void onBindViewHolder(final OtherBlogsAdapter.ViewHolder holder, int position) {

        BlogsModel model = blogItems.get(position);
        final String title=model.getTitle();
        final String text=model.getText();
        final String link=model.getLink();
        final String url="http://tsamali.ge/"+model.getImg();

        holder.text.setText(title);
        ViewTreeObserver vto = holder.text.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                ViewTreeObserver obs = holder.text.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (holder.text.getLineCount() > 3) {
                    int lineEndIndex = holder.text.getLayout().getLineEnd(2);
                    String text = holder.text.getText().subSequence(0, lineEndIndex - 3) + "...";
                    holder.text.setText(text);
                }
            }
        });


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
                intent.putExtra("link",link);
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
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
        ImageView image;
        LinearLayout blog;

        // ImageView image_background;
        public ViewHolder(final View itemView) {
            super(itemView);

            text = (TextView) itemView.findViewById(R.id.text);
            image = (ImageView) itemView.findViewById(R.id.image);
            blog=(LinearLayout) itemView.findViewById(R.id.blog);

        }
    }

    }
