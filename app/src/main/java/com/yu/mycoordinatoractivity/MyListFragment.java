package com.yu.mycoordinatoractivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Walt_Yu on 2017/8/18.
 */

public class MyListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.fragement_list, container, false);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setAdapter(new CheeseAdapter(this.getActivity(), getRandomSublist(Cheeses.sCheeseStrings, 30)));
        return rv;
    }

    private List<String> getRandomSublist(String[] array, int amount) {
        ArrayList<String> list = new ArrayList<>(amount);
        Random random = new Random();
        while (list.size() < amount) {
            list.add(array[random.nextInt(array.length)]);
        }
        return list;
    }

public static class CheeseAdapter extends RecyclerView.Adapter<CheeseAdapter.CheesViewHolder> {

    private List<String> mValues;

    public static class CheesViewHolder extends RecyclerView.ViewHolder{
        public final ImageView mImageView;
        public final TextView mTextView;
        public final View mView;
        public String mBoundString;
        public CheesViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mImageView = (ImageView) itemView.findViewById(R.id.avatar);
            mTextView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }

    public CheeseAdapter(Context context,  List<String> items) {
        mValues = items;
    }

    @Override
    public CheesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_item, parent, false);
        return new CheesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CheesViewHolder holder, int position) {
        holder.mTextView.setText(mValues.get(position));
        holder.mBoundString =mValues.get(position);

        Glide.with(holder.mImageView.getContext())
                .load(Cheeses.getRandomCheeseDrawable())
                .fitCenter()
                .into(holder.mImageView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, CheesesDetailActivity.class);
                intent.putExtra(CheesesDetailActivity.EXTRA_NAME, holder.mBoundString);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


}
}
