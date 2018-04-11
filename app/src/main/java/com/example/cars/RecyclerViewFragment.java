package com.example.cars;

// Ahmed Alotaibi
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.florent37.hollyviewpager.HollyViewPagerBus;
import butterknife.Bind;
import butterknife.ButterKnife;
public class RecyclerViewFragment extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View
    onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.
                        fragment_recyclerview,
                container, false);}
    /*Called after onCreateView has returned, but before any
    saved state has been restored in to the view.*/
    @Override
    public void
    onViewCreated(
            @NonNull View view,
            Bundle savedInstanceState) {
        super.onViewCreated(view,
                savedInstanceState);
        ButterKnife.bind(this,view);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(
                new RecyclerAdapter());
        HollyViewPagerBus.
                registerRecyclerView(
                        getActivity(),
                        recyclerView);}}