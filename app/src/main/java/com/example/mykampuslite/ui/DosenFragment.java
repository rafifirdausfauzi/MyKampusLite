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

public class DosenFragment extends Fragment {

    private AkademikViewModel viewModel;
    private AkademikListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dosen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(AkademikViewModel.class);

        EditText etNidn = view.findViewById(R.id.etNidn);
        EditText etNama = view.findViewById(R.id.etNama);
        EditText etMatkul = view.findViewById(R.id.etMatkul);
        Button btnSimpan = view.findViewById(R.id.btnSimpan);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewDosen);

        adapter = new AkademikListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        viewModel.getAllDosen().observe(getViewLifecycleOwner(), dosenList -> {
            adapter.submitList(new ArrayList<>(dosenList));
        });

        btnSimpan.setOnClickListener(v -> {
            String nidn = etNidn.getText().toString();
            String nama = etNama.getText().toString();
            String matkulStr = etMatkul.getText().toString();

            try {
                int matkul = matkulStr.isEmpty() ? 0 : Integer.parseInt(matkulStr);
                viewModel.tambahDosen(nidn, nama, matkul);
                
                etNidn.setText("");
                etNama.setText("");
                etMatkul.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Jumlah mata kuliah harus angka", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
