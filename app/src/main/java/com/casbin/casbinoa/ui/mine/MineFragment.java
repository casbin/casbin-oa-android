package com.casbin.casbinoa.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.casbin.casbinoa.R;
import com.casbin.casbinoa.databinding.FragmentMineBinding;
import com.casbin.casbinoa.ui.home.HomeFragment;
import com.casbin.casdoor.CasdoorAuth;
import com.casbin.casdoor.CasdoorConfig;
import com.casbin.casdoor.CasdoorLoginActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MineFragment extends Fragment {

    private FragmentMineBinding binding;

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMineBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getActivity() != null) {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (CasdoorAuth.hasLoggedIn(getActivity())) {
                UserInfoFragment userInfoFragment = new UserInfoFragment(getActivity());
                fragmentTransaction.replace(R.id.minePage, userInfoFragment);
            } else {
                UserLoginFragment userLoginFragment = new UserLoginFragment(getActivity());
                fragmentTransaction.replace(R.id.minePage, userLoginFragment);
            }
            fragmentTransaction.commit();
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}