package com.jrektor.restapi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Products> products;
    private Context context;
    private ViewHolder.RecyclerViewClickListener listener;

    public Adapter(List<Products> products, Context context, ViewHolder.RecyclerViewClickListener listener) {
        this.products = products;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

        return new ViewHolder(view, listener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.name.setText(products.get(position).getName());
        holder.price.setText(products.get(position).getPrice());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_camera);
        requestOptions.error(R.drawable.ic_camera);

        Glide.with(context)
                .load(products.get(position).getImage())
                .apply(requestOptions)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener recyclerView;
        private CircleImageView imageView;
        private TextView name, price;
        private CardView rowContainer;

        public ViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_main);
            name = itemView.findViewById(R.id.name_main);
            price = itemView.findViewById(R.id.price_main);
            rowContainer = itemView.findViewById(R.id.row_container);

            recyclerView = listener;
            rowContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerView.onRowClick(rowContainer, getAdapterPosition());
        }

        public interface RecyclerViewClickListener{

            void onRowClick(CardView rowContainer, int adapterPosition);
        }
    }
}
