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
import com.smartpan.smartpaninterviewtask.databinding.ItemCountryBinding;
import com.smartpan.smartpaninterviewtask.models.Country;

public class CountriesAdapter extends ListAdapter<Country, CountriesAdapter.PostHolder> {

//    OnItemClickListener listener;

    LayoutInflater layoutInflater;

    public CountriesAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Country> DIFF_CALLBACK = new DiffUtil.ItemCallback<Country>() {
        @Override
        public boolean areItemsTheSame(Country oldItem, Country newItem) {
            return oldItem.getNumericCode() == newItem.getNumericCode();
        }

        @Override
        public boolean areContentsTheSame(Country oldItem, Country newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    };

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemCountryBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_country, parent, false);
        return new PostHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {

        holder.binding.setCountry(getItem(position));
    }

    public class PostHolder extends RecyclerView.ViewHolder {

        private final ItemCountryBinding binding;

        public PostHolder(final ItemCountryBinding itemBinding) {
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
        void onItemClick(Country note);
    }

//    public void setOnItemClickListener(OnItemClickListener listner) {
//        this.listener = listner;
//    }
}
