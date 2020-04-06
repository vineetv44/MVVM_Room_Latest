package com.all_latest_in_android.ui.headline;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.all_latest_in_android.R;
import com.all_latest_in_android.data.local.entity.HeadlineEntity;
import com.all_latest_in_android.data.remote.Status;
import com.all_latest_in_android.databinding.FragmentListHeadlinesBinding;
import com.all_latest_in_android.ui.adapter.HeadlineAdapter;
import com.all_latest_in_android.ui.base.BaseFragment;
import com.all_latest_in_android.ui.callbacks.HeadlineListCallback;
import com.all_latest_in_android.ui.viewmodel.HeadlineViewModel;
import com.all_latest_in_android.utils.Constants;
import com.all_latest_in_android.utils.FragmentUtils;

import java.util.Objects;


public class HeadlineFragment extends BaseFragment<HeadlineViewModel, FragmentListHeadlinesBinding> implements HeadlineListCallback {

    public static HeadlineFragment newInstance() {
        Bundle args = new Bundle();
        HeadlineFragment fragment = new HeadlineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<HeadlineViewModel> getViewModel() {
        return HeadlineViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_list_headlines;
    }

    @Override
    public void onHeadlineItemClicked(HeadlineEntity headlineEntity) {
        if (null != getActivity()) {
            Bundle args = new Bundle();
            args.putString(Constants.BUNDLE_KEY_HEADLINE_TITLE, headlineEntity.getTitle());
            args.putString(Constants.BUNDLE_KEY_HEADLINE_AUTHOR, headlineEntity.getAuthor());
            args.putString(Constants.BUNDLE_KEY_HEADLINE_PUBLISHED_DATE, headlineEntity.getPublishedAt());
            args.putString(Constants.BUNDLE_KEY_HEADLINE_DESCRIPTION, headlineEntity.getDescription());
            args.putString(Constants.BUNDLE_KEY_HEADLINE_URL, headlineEntity.getUrl());
            args.putString(Constants.BUNDLE_KEY_HEADLINE_IMAGE_URL, headlineEntity.getUrlToImage());
            HeadlineDetailFragment headlineDetailFragment = new HeadlineDetailFragment();
            headlineDetailFragment.setArguments(args);
            FragmentUtils.replaceFragment((AppCompatActivity) getActivity(), headlineDetailFragment, R.id.fragContainer, true, FragmentUtils.TRANSITION_SLIDE_LEFT_RIGHT);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.recyclerView.setAdapter(new HeadlineAdapter(this));
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getHeadlineResourceData()
                .observe(this, listResource -> {
                    if (null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)) {
                        dataBinding.progressBar.setVisibility(View.GONE);
                    }
                    dataBinding.setResourceValue(listResource);

                    // If the cached data is already showing then no need to show the error
                    if (null != dataBinding.recyclerView.getAdapter() && dataBinding.recyclerView.getAdapter().getItemCount() > 0) {
                        dataBinding.errorText.setVisibility(View.GONE);
                    }
                });

        Fragment f = Objects.requireNonNull(getActivity()).getSupportFragmentManager().findFragmentById(R.id.fragContainer);
        if(f instanceof HeadlineFragment) {
            Window window = Objects.requireNonNull(getActivity().getWindow());
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }
    }
}

