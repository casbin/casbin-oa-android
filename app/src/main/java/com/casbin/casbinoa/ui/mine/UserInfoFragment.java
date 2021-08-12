package com.casbin.casbinoa.ui.mine;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.casbin.casbinoa.R;
import com.casbin.casbinoa.databinding.FragmentUserInfoBinding;
import com.casbin.casdoor.CasdoorAuth;
import com.casbin.casdoor.CasdoorBackend;
import com.casbin.casdoor.CasdoorClaims;
import com.casbin.casdoor.CasdoorUserToken;

public class UserInfoFragment extends Fragment {

    private FragmentActivity activity;
    private FragmentUserInfoBinding binding;

    public UserInfoFragment(FragmentActivity activity) {
        // Required empty public constructor
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentUserInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        String userToken = CasdoorUserToken.GetUserToken(getActivity());
        CasdoorClaims casdoorClaims = CasdoorUserToken.ParseJwtToken(getActivity(), userToken);

        TextView name = binding.UserInfoTableName;
        name.setText(casdoorClaims.getName());

        TextView email = binding.UserInfoTableEmail;
        email.setText(casdoorClaims.getEmail());

        TextView phone = binding.UserInfoTablePhone;
        phone.setText(casdoorClaims.getPhone());

        TextView organization = binding.UserInfoTableOrganization;
        organization.setText(casdoorClaims.getOrganization());

        Button button = binding.button;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CasdoorAuth.logout(activity);
            }
        });

        return root;
    }
}