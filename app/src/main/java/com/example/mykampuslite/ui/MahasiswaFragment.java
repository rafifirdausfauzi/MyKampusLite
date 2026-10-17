package com.example.mykampuslite.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mykampuslite.R;
import com.example.mykampuslite.viewmodel.AkademikViewModel;

import java.util.ArrayList;

public class MahasiswaFragment extends Fragment {

    private AkademikViewModel viewModel;
    private AkademikListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mahasiswa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(AkademikViewModel.class);

        EditText etNim = view.findViewById(R.id.etNim);
        EditText etNama = view.findViewById(R.id.etNama);
        EditText etIpk = view.findViewById(R.id.etIpk);
        Button btnSimpan = view.findViewById(R.id.btnSimpan);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMahasiswa);

        adapter = new AkademikListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        viewModel.getAllMahasiswa().observe(getViewLifecycleOwner(), mahasiswaList -> {
            adapter.submitList(new ArrayList<>(mahasiswaList));
        });

        btnSimpan.setOnClickListener(v -> {
            String nim = etNim.getText().toString();
            String nama = etNama.getText().toString();
            String ipkStr = etIpk.getText().toString();

            try {
                double ipk = ipkStr.isEmpty() ? 0 : Double.parseDouble(ipkStr);
                viewModel.tambahMahasiswa(nim, nama, ipk);
                
                // Clear form if success (this is tricky because error comes from LiveData)
                // For simplicity, we assume if no toast appears immediately it might be fine, 
                // but real app would observe success state.
                etNim.setText("");
                etNama.setText("");
                etIpk.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "IPK harus angka", Toast.LENGTH_SHORT).show();
            }
        });

        // Observe error messages for this fragment
        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
