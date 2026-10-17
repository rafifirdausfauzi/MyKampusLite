package com.example.mykampuslite.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mykampuslite.R;
import com.example.mykampuslite.model.Person;

public class AkademikListAdapter extends ListAdapter<Person, AkademikListAdapter.AkademikViewHolder> {

    public AkademikListAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Person> DIFF_CALLBACK = new DiffUtil.ItemCallback<Person>() {
        @Override
        public boolean areItemsTheSame(@NonNull Person oldItem, @NonNull Person newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals") // Tambahkan baris ini
        @Override
        public boolean areContentsTheSame(@NonNull Person oldItem, @NonNull Person newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public AkademikViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_akademik, parent, false);
        return new AkademikViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AkademikViewHolder holder, int position) {
        Person currentPerson = getItem(position);

        holder.tvNama.setText(currentPerson.getNama());
        holder.tvRole.setText(currentPerson.getRole());
        holder.tvStatus.setText(currentPerson.getStatusAkademik());
    }

    class AkademikViewHolder extends RecyclerView.ViewHolder {
        final TextView tvNama;
        final TextView tvRole;
        final TextView tvStatus;

        public AkademikViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvRole = itemView.findViewById(R.id.tvRole);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
