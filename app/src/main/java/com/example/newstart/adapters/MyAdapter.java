package com.example.newstart.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.newstart.R;
import com.example.newstart.views.ScrollConstraintLayout;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<String> mList;
    private OnDeleteListener mListener;

    private ScrollConstraintLayout mLastView;

    public MyAdapter(List<String> list, OnDeleteListener listener) {
        this.mList = list;
        this.mListener = listener;
    }

    @Override
    public int getCount() {
        return null != mList ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null != mList ? mList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.layout_item, null);
            holder = new ViewHolder();
            holder.mTv = convertView.findViewById(R.id.tv_left);
            holder.mDelete = convertView.findViewById(R.id.btn_delete);
            holder.mRoot = convertView.findViewById(R.id.root);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTv.setText(mList.get(position));
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onDeleteClicked(position);
                }
            }
        });

        holder.mRoot.scrollTo(0, 0);
        holder.mRoot.setListener(new ScrollConstraintLayout.OnScrollListener() {
            @Override
            public void onScrolled(ScrollConstraintLayout layout) {
                if (null != mLastView) {
                    mLastView.startScroll(mLastView.getScrollX(), -mLastView.getScrollX());
                }
                mLastView = layout;
            }
        });

        return convertView;
    }

    static class ViewHolder {
        private AppCompatTextView mTv;
        private AppCompatButton mDelete;
        private ScrollConstraintLayout mRoot;
    }

    public interface OnDeleteListener {
        void onDeleteClicked(int pos);
    }
}
