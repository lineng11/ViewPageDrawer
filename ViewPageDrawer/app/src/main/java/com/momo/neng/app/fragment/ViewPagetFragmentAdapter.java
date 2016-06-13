package com.momo.neng.app.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.momo.neng.app.R;
import com.momo.neng.app.recyclerViewpager.helper.ItemTouchHelperAdapter;

import java.util.List;


public class ViewPagetFragmentAdapter extends RecyclerView.Adapter<ViewPagetFragmentAdapter.ViewHolder> implements ItemTouchHelperAdapter, View.OnClickListener {


    private Context mContext;
    private List<String> list;

    public ViewPagetFragmentAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Log.e("onItemMove","fromPosition: "+fromPosition+"  toPosition: "+toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        Log.e("onItemDismiss","position: "+position);
        list.remove(position);
        notifyItemRemoved(position);
//        list.remove(position);
    }

    @Override
    public void onClick(View v) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
        }
    }
}
