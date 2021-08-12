package com.casbin.casbinoa.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.casbin.casbinoa.R;
import com.casbin.casbinoa.databinding.FragmentUserLoginBinding;
import com.casbin.casdoor.CasdoorAuth;
import com.casbin.casdoor.CasdoorConfig;
import com.casbin.casdoor.CasdoorLoginActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class UserLoginFragment extends Fragment {
    private FragmentActivity activity;
    private FragmentUserLoginBinding binding;

    public UserLoginFragment(FragmentActivity activity) {
        // Required empty public constructor
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (CasdoorAuth.hasLoggedIn(activity)) {
            Intent intent = activity.getIntent();
            activity.finish();
            activity.startActivity(intent);
        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentUserLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        textView.setText(R.string.login_notification);

        final Button loginBtn = binding.loginBtn;
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(activity, CasdoorLoginActivity.class);

                startActivity(intent);
            }
        });

        return root;
    }
}