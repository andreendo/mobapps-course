package com.example.boardofmessagesapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boardofmessagesapp.R;
import com.example.boardofmessagesapp.databinding.FragmentShowMessagesBinding;
import com.example.boardofmessagesapp.repository.Message;

public class MessageRecyclerViewAdapter extends ListAdapter<Message, MessageRecyclerViewAdapter.ViewHolder> {

    private ItemClickListener mClickListener;
    private ItemLongClickListener mLongClickListener;

    public MessageRecyclerViewAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                FragmentShowMessagesBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message m = getItem(position);
        holder.fromTextView.setText("FROM: " + m.getFrom());
        holder.toTextView.setText("TO: " + m.getTo());
        holder.timestampTextView.setText(m.getTimestamp());
        holder.messageTextView.setText("MESSAGE: " + m.getText());
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView fromTextView, toTextView, timestampTextView, messageTextView;

        ViewHolder(FragmentShowMessagesBinding binding) {
            super(binding.getRoot());
            fromTextView = binding.from;
            toTextView = binding.to;
            timestampTextView = binding.timestamp;
            messageTextView = binding.message;
            binding.container.setOnClickListener(this);
            binding.container.setOnLongClickListener(this);
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

    public static final DiffUtil.ItemCallback<Message> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Message>() {
                // it decides if two objects are equal
                @Override
                public boolean areItemsTheSame(Message oldItem, Message newItem) {
                    return oldItem.getTimestamp().equals(newItem.getTimestamp());
                }
                // it decides if the object needs to redraw
                @Override
                public boolean areContentsTheSame(Message oldItem, Message newItem) {
                    return oldItem.getTimestamp().equals(newItem.getTimestamp());
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