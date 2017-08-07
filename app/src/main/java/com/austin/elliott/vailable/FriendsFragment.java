package com.austin.elliott.vailable;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Tiger on 8/6/17.
 */

public class FriendsFragment extends Fragment {

    private TextView friendsSearchField;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        Button searchFriendsButton = (Button) findViewById(R.id.searchFriendsButton);
//        friendsSearchField = (TextView) findViewById(R.id.friendsSearchField);
//
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {

//        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.friends_search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

    }
}
