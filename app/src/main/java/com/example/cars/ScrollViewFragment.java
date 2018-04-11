package com.example.cars;
// Ahmed Alotaibi

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.florent37.hollyviewpager.HollyViewPagerBus;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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
    @Bind(R.id.intImageView)
    ImageView intImageView;
    @Bind(R.id.extImageView)
    ImageView extImageView;
    @Bind(R.id.upholsteryImageView)
    ImageView upholsteryImageView;
    @Bind(R.id.trimImageView)
    ImageView trimImageView;
    @Bind(R.id.rimImageView)
    ImageView rimImageView;
    @Bind(R.id.engineImageView)
    ImageView engineImageView;
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
                 ViewGroup container,
                 Bundle savedInstanceState) {
        // loadImages(VIN);
        return inflater.inflate(R.layout.fragment_scroll,
                container, false);}
    /*Called after onCreateView has returned, but before any
      saved state has been restored in to the view.*/
    @Override
    public void
    onViewCreated(@NonNull View view,
                  Bundle savedInstanceState) {
        super.onViewCreated(view,
                savedInstanceState);
        ButterKnife.bind(this, view);
        assert getArguments() != null;
        String titleString =
                getArguments().getString("title");
        String vin;
        title.setText(titleString);
        assert titleString != null;
        switch (titleString) {
            case "Merc" +
                    "edes 0" :
                vin = "/WDD2130" +
                        "331A123456/";break;
            default:
                vin = "/WDD2130" +
                        "331A123456/";break;}
        loadImages(vin,
                "vehicle");
        loadImages(vin,
                "components");
        //loadImages(vin,"components/equipments");
        HollyViewPagerBus.registerScrollView(getActivity(), scrollView);}
    private void
    loadImageFromUrl(String url,ImageView imageView)
    {Picasso.with(getContext())
            .load(url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess(){}
                    @Override
                    public void onError() {}});}
    @SuppressLint("StaticFieldLeak")
    private void
    loadImages(final String vin,
               final String specificUrl) {
        new AsyncTask<URL,
                Void,
                List<String>>() {
            @Override
            protected List<String> doInBackground(URL... urls) {
                // Create URL object
                URL urlVehicle = null;return null;}
            @Override
            protected void
            onPostExecute(List<String> vehicleImages) {}
        }.execute();}
    private static
    List<String> extractImagesFromJson(
            String jsonResponse,
            String specificUrl) { return null;}
    private static String
    readFromStream(InputStream inputStream)
            throws IOException {return null;}}
