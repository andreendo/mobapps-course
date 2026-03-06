package com.example.todov3app.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todov3app.R;
import com.example.todov3app.model.Task;

// ListAdapter is more convenient to handle a list of items that may change.
// https://github.com/codepath/android_guides/wiki/Using-the-RecyclerView#using-with-listadapter
// https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter
public class TasksAdapter extends ListAdapter<Task, TasksAdapter.ViewHolder> {

    private ItemClickListener mClickListener;
    private ItemLongClickListener mLongClickListener;

    public TasksAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new TasksAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String label = getItem(position).getName();
        holder.myTextView.setText(label);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.itemTextView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            if (mLongClickListener != null) {
                mLongClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
            return false;
        }
    }

    public static final DiffUtil.ItemCallback<Task> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Task>() {
                // it decides if two objects are equal
                @Override
                public boolean areItemsTheSame(Task oldItem, Task newItem) {
                    return oldItem.getName().equals(newItem.getName());
                }
                // it decides if the object needs to redraw
                @Override
                public boolean areContentsTheSame(Task oldItem, Task newItem) {
                    return (oldItem.getName().equals(newItem.getName()) &&
                            oldItem.getDescription().equals(newItem.getDescription()));
                }
            };

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // allows long clicks events
    void setLongClickListener(ItemLongClickListener itemLongClickListener) {
        this.mLongClickListener = itemLongClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface ItemLongClickListener {
        void onItemLongClick(View view, int position);
    }
}
