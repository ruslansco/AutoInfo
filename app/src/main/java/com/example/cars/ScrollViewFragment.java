package com.example.cars;
// Modified By Ahmed Alotaibi
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.florent37.hollyviewpager.HollyViewPagerBus;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ScrollViewFragment
        extends Fragment {
    private static final
    String TAG = "Main"+
            "Activity";
    private static final
    String MERCEDES_VEHICLE_IMAGES_ENDPOINT =
            "https://api.mercedes-benz.com" +
                    "/image/v1/vehicles";
    private static final
    String MERCEDES_API_KEY =
            "c321c388-421e-" +
                    "4e1f-87d0-62b05593d50c";
    @Bind(R.id.scrollView)
    ObservableScrollView scrollView;
    @Bind(R.id.title)
    TextView title;
    public static ScrollViewFragment
    newInstance(String title){
        Bundle args = new Bundle();
        args.putString("title",title);
        ScrollViewFragment fragment =
                new ScrollViewFragment();
        fragment.setArguments(args);
        return fragment;}
    @Nullable
    @Override
    public View
    onCreateView(@NonNull LayoutInflater inflater,
                 ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scroll,
                container, false);}
    /*Called after onCreateView has returned, but before any
      saved state has been restored in to the view.*/
    @Override
    public void onViewCreated(@NonNull View view,
                              Bundle savedInstanceState) {
        super.onViewCreated(view,
                savedInstanceState);
        ButterKnife.bind(this, view);
        assert getArguments() != null;
        title.setText(getArguments().getString("title"));
        HollyViewPagerBus.registerScrollView(getActivity(), scrollView);}}