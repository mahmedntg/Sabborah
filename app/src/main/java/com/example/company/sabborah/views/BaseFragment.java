package com.example.company.sabborah.views;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.company.sabborah.R;
import com.example.company.sabborah.presenters.UserView;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.utils.MyAlertDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseFragment extends Fragment {
    private ProgressDialog progressDialog;
    private AlertDialog alertDialog;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.progressTitle));
        alertDialog = MyAlertDialog.createAlertDialog(getContext(), getString(R.string.register_validation));
        return view;
    }

    protected int getFragmentLayoutId() {
        return 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
