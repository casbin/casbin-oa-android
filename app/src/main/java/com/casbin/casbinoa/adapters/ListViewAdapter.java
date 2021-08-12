package com.casbin.casbinoa.adapters;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.casbin.casbinoa.R;
import com.casbin.casdoor.CasdoorAuth;
import com.casbin.casdoor.CasdoorClaims;
import com.casbin.casdoor.CasdoorUser;
import com.casbin.casdoor.CasdoorUserToken;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.InnerHolder> {

    ArrayList<CasdoorUser> Users;
    FragmentActivity Activity;

    public ListViewAdapter(ArrayList<CasdoorUser> users, FragmentActivity activity) {
        Users = users;
        Activity = activity;
    }

    @NonNull
    @NotNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_user, null);
        return new InnerHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(@NonNull @NotNull ListViewAdapter.InnerHolder holder, int position) {
        holder.setData(Users.get(position));
    }

    @Override
    public int getItemCount() {
        if (Users != null) {
            return Users.size();
        }
        return 0;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private final TextView username;
        private final TextView email;
        private final TextView phone;

        public InnerHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.item_user_username);
            email = itemView.findViewById(R.id.item_user_email);
            phone = itemView.findViewById(R.id.item_user_phone);
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public void setData(CasdoorUser casdoorUser) {
            username.setText(casdoorUser.getDisplayName());

            if (CasdoorAuth.hasLoggedIn(Activity)) {
                email.setText(casdoorUser.getEmail());
            } else {
                email.setText("*************");
            }

            String userToken = CasdoorUserToken.GetUserToken(Activity);
            CasdoorClaims casdoorClaims = CasdoorUserToken.ParseJwtToken(Activity, userToken);

            if (CasdoorAuth.hasLoggedIn(Activity) && casdoorClaims.isAdmin()) {
                phone.setText(casdoorUser.getPhone());
            } else {
                phone.setText("*********");
            }
        }
    }
}
