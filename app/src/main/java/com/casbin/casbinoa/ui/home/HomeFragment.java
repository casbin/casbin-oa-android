package com.casbin.casbinoa.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.casbin.casbinoa.R;
import com.casbin.casbinoa.databinding.FragmentHomeBinding;
import com.casbin.casdoor.CasdoorAuth;
import com.casbin.casdoor.CasdoorClaims;
import com.casbin.casdoor.CasdoorLoginActivity;
import com.casbin.casdoor.CasdoorUserToken;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;

        if (CasdoorAuth.hasLoggedIn(getActivity())) {
            String userToken = CasdoorUserToken.GetUserToken(getActivity());
            CasdoorClaims casdoorClaims = CasdoorUserToken.ParseJwtToken(getActivity(), userToken);
            textView.setText("Welcome, " + casdoorClaims.getName() + "!");
        } else {
            textView.setText("Guest Mode");
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}