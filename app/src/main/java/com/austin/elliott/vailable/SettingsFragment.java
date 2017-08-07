package com.austin.elliott.vailable;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Button logoutButton = (Button) view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmLogoutDialog();
            }

            private void confirmLogoutDialog() {
                //show a dialog box confirming logout action
                new AlertDialog.Builder(getActivity())
                        .setTitle("Logging Out")
                        .setMessage("Are you sure you want to log out?")
                        .setIcon(R.drawable.ic_warning_black_36dp)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                logout();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).create()
                        .show();
            }
        });

        return view;
    }

    private void logout() {
        LoginManager.getInstance().logOut();
        FirebaseAuth.getInstance().signOut();
        MiscUtils.switchToActivityClearBackStack(getActivity(), LoginActivity.class);
    }

}
