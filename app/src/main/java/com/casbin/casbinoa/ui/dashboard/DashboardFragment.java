package com.casbin.casbinoa.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.casbin.casbinoa.R;
import com.casbin.casbinoa.adapters.ListViewAdapter;
import com.casbin.casbinoa.databinding.FragmentDashboardBinding;
import com.casbin.casdoor.CasdoorUser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private RecyclerView recyclerView;

    @Override
    public void onStart() {
        super.onStart();

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.recycle;

        try {
            ArrayList<CasdoorUser> users = CasdoorUser.GetUsers(getActivity());
            if (users != null) {

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);

                ListViewAdapter listViewAdapter = new ListViewAdapter(users, getActivity());
                recyclerView.setAdapter(listViewAdapter);
            } else {
                recyclerView.setVisibility(View.GONE);
            }
        } catch (JSONException | InterruptedException e) {
            e.printStackTrace();
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}