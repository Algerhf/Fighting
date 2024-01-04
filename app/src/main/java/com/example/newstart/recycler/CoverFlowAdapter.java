package com.example.newstart.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newstart.R;

import java.util.List;

public class CoverFlowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private List<String> mList;

    private int[] mPics = {R.mipmap.item1, R.mipmap.item2, R.mipmap.item3, R.mipmap.item4, R.mipmap.item5, R.mipmap.item6};

    public CoverFlowAdapter(Context context, List<String> list) {
        this.mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new CoverFlowViewHolder(inflater.inflate(R.layout.item_coverflow, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CoverFlowViewHolder coverFlowViewHolder = (CoverFlowViewHolder) holder;
        coverFlowViewHolder.mTv.setText("item " + position);
        coverFlowViewHolder.mIv.setImageDrawable(mContext.getResources().getDrawable(mPics[position % mPics.length]));
    }

    @Override
    public int getItemCount() {
        return null != mList ? mList.size() : 0;
    }

    public class CoverFlowViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView mTv;
        private AppCompatImageView mIv;

        public CoverFlowViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.item_text);
            mIv = itemView.findViewById(R.id.item_img);

            mTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mTv.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
