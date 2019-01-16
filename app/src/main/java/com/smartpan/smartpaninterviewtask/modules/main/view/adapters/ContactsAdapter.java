package com.smartpan.smartpaninterviewtask.modules.main.view.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartpan.smartpaninterviewtask.R;
import com.smartpan.smartpaninterviewtask.databinding.ItemContactBinding;
import com.smartpan.smartpaninterviewtask.models.PhoneContact;

public class ContactsAdapter extends ListAdapter<PhoneContact, ContactsAdapter.PhoneContactHolder> {

//    OnItemClickListener listener;

    LayoutInflater layoutInflater;

    public ContactsAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<PhoneContact> DIFF_CALLBACK = new DiffUtil.ItemCallback<PhoneContact>() {
        @Override
        public boolean areItemsTheSame(PhoneContact oldItem, PhoneContact newItem) {
            return oldItem.getPhoneNumber() == newItem.getPhoneNumber();
        }

        @Override
        public boolean areContentsTheSame(PhoneContact oldItem, PhoneContact newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    };

    @NonNull
    @Override
    public PhoneContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContactBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_contact, parent, false);
        return new PhoneContactHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneContactHolder holder, int position) {

        holder.binding.setContact(getItem(position));
    }

    public class PhoneContactHolder extends RecyclerView.ViewHolder {

        private final ItemContactBinding binding;

        public PhoneContactHolder(final ItemContactBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
//                    listener.onItemClick(notes.get(position));
//                    listener.onItemClick(getItem(position));
                }
            });
        }

    }

    public interface OnItemClickListener {
        void onItemClick(PhoneContact note);
    }

//    public void setOnItemClickListener(OnItemClickListener listner) {
//        this.listener = listner;
//    }
}
