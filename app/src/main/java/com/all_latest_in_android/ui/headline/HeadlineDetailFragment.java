package com.all_latest_in_android.ui.headline;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.all_latest_in_android.R;
import com.all_latest_in_android.databinding.FragmentHeadlineDetailsBinding;
import com.all_latest_in_android.ui.base.BaseFragment;
import com.all_latest_in_android.ui.viewmodel.HeadlineDetailsViewModel;
import com.all_latest_in_android.utils.CommonUtils;
import com.all_latest_in_android.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.Objects;

public class HeadlineDetailFragment extends BaseFragment<HeadlineDetailsViewModel, FragmentHeadlineDetailsBinding> {
    public static HeadlineDetailFragment newInstance() {
        Bundle args = new Bundle();
        HeadlineDetailFragment fragment = new HeadlineDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<HeadlineDetailsViewModel> getViewModel() {
        return HeadlineDetailsViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_headline_details;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Fragment f = Objects.requireNonNull(getActivity()).getSupportFragmentManager().findFragmentById(R.id.fragContainer);

        if (f instanceof HeadlineDetailFragment) {
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }



        Bundle args = getArguments();
        if (null != args) {
            dataBinding.loadingProgress.setVisibility(View.GONE);
            dataBinding.textTitle.setText(args.getString(Constants.BUNDLE_KEY_HEADLINE_TITLE));
            dataBinding.textPublishedAt.setText(CommonUtils.getFormatedDate(args.getString(Constants.BUNDLE_KEY_HEADLINE_PUBLISHED_DATE)));
            dataBinding.textHeadlineDesc.setText(args.getString(Constants.BUNDLE_KEY_HEADLINE_DESCRIPTION));
            dataBinding.textAuthor.setText(args.getString(Constants.BUNDLE_KEY_HEADLINE_AUTHOR));

            Glide.with(Objects.requireNonNull(getActivity()))
                    .load(args.getString(Constants.BUNDLE_KEY_HEADLINE_IMAGE_URL))
                    .into(dataBinding.imageViewBackground);


            //Not needed here, since could also be fetch via bundle(Fetching Data from db)
            /*viewModel.setUrl(args.getString(Constants.BUNDLE_KEY_HEADLINE_URL));
            viewModel.loadHeadlineDetails();*/
        }

        dataBinding.relativeLayoutBack.setOnClickListener(view -> getActivity().onBackPressed());

        //Not needed here, since could also be fetch via bundle(Fetches data from db)
        /*viewModel.getHeadlineEntityMutableLiveData().observe(this, headlineEntity -> {
            if (null != headlineEntity && null != args) {
                dataBinding.textTitle.setText(args.getString(Constants.BUNDLE_KEY_HEADLINE_TITLE));
                dataBinding.textPublishedAt.setText(CommonUtils.getFormatedDate(args.getString(Constants.BUNDLE_KEY_HEADLINE_PUBLISHED_DATE)));
                dataBinding.textHeadlineDesc.setText(args.getString(Constants.BUNDLE_KEY_HEADLINE_DESCRIPTION));
                dataBinding.textAuthor.setText(args.getString(Constants.BUNDLE_KEY_HEADLINE_AUTHOR));

                Glide.with(Objects.requireNonNull(getActivity()))
                        .load(args.getString(Constants.BUNDLE_KEY_HEADLINE_IMAGE_URL))
                        .into(dataBinding.imageViewBackground);

                dataBinding.loadingProgress.setVisibility(View.GONE);
            }
        });*/

        viewModel.getErrorMessageRecieved().observe(this, message -> {
            dataBinding.loadingProgress.setVisibility(View.GONE);
            dataBinding.textAuthor.setVisibility(View.GONE);
            dataBinding.textPublishedAt.setVisibility(View.GONE);
            dataBinding.textHeadlineDesc.setVisibility(View.GONE);
            dataBinding.textTitle.setText(Objects.requireNonNull(getActivity()).getString(R.string.networkError));
            dataBinding.textTitle.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_color_toolbar));
        });
    }
}
