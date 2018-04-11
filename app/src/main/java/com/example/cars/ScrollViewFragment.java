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
    String TAG = "Main" +
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
    newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
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
                    "edes 0":
                vin = "/WDD2130" +
                        "331A123456/";break;
            case "Merc" +
                    "edes 1" :
                vin = "/WDD2462" +
                        "421N123456/";break;
            case "Merc" +
                    "edes 2" :
                vin = "/WDC15694" +
                        "31J123456/";break;
            case "Merc" +
                    "edes 3" :
                vin = "/WDD2174" +
                        "821A123456/";break;
            case "Merc" +
                    "edes 4" :
                vin = "/WME4533" +
                        "441K012345/";break;
            default:
                vin = "/WDD2130" +
                        "331A123456/";break;}
        loadImages(vin,
                "vehicle");
        loadImages(vin,
                "components");
        //loadImages(vin,"components/equipments");
        HollyViewPagerBus.registerScrollView(getActivity(), scrollView);}
    // Picasso download the image in
    // another thread and it manages:
    // the placeholder in the meantime
    // the image is still downloading
    // resizing/cropping/centering/scaling/
    private void
    loadImageFromUrl(String url, ImageView imageView) {
        Picasso.with(getContext())
                .load(url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {}
                    @Override
                    public void onError() {}});}
    @SuppressLint("StaticFieldLeak")
    private void
    loadImages(final String vin,
               final String specificUrl) {
        new AsyncTask <URL,
                Void,
                List <String>>() {
            // Override this method to perform
            // a computation on a background thread.
            @Override
            protected List <String> doInBackground(URL... urls) {
                // Create URL object
                URL urlVehicle = null;
                try {
                    urlVehicle = new URL(MERCEDES_VEHICLE_IMAGES_ENDPOINT + vin +
                            specificUrl + "?apikey=" + MERCEDES_API_KEY);}catch
                        (MalformedURLException e){e.printStackTrace();}
                // Perform HTTP request to the URL and
                // receive a JSON response back
                String jsonResponse = "";
                try {
                    jsonResponse = makeHttpRequest(urlVehicle);}catch(IOException e){
                    // Handle the IOException
                    Log.e(TAG, "IOException is occurred", e);}
                // Extract relevant fields from the JSON response
                // and create a String synonyms object
                List <String> vehicleImages =
                        extractImagesFromJson(jsonResponse,
                                specificUrl);
                return vehicleImages;}
                // Runs on the UI thread
                // after doInBackground(URL...).
            @Override
            protected void
            onPostExecute(List <String> vehicleImages) {
                if (vehicleImages == null) {
                    return;
                }
                if (specificUrl.equals("vehicle")) {
                    loadImageFromUrl(vehicleImages.get(0), intImageView);
                    loadImageFromUrl(vehicleImages.get(1), extImageView);
                } else if (specificUrl.equals("components")) {
                    loadImageFromUrl(vehicleImages.get(0), upholsteryImageView);
                    loadImageFromUrl(vehicleImages.get(1), trimImageView);
                    loadImageFromUrl(vehicleImages.get(2), rimImageView);
                    loadImageFromUrl(vehicleImages.get(3), engineImageView);}
            }}.execute();}
            // Gets the images from Json Response
    private static List <String>
    extractImagesFromJson(
            String jsonResponse,
            String specificUrl) {
        try {
            if (specificUrl.equals("vehicle")) {
                List <String> vehicleImages =
                        new ArrayList <>();
                JSONObject baseJsonResponse =
                        new JSONObject(jsonResponse);
                JSONObject intImageJSONObject =
                        baseJsonResponse.getJSONObject("vehicle")
                                .getJSONObject("INT1");
                JSONObject extImageJSONObject =
                        baseJsonResponse.getJSONObject("vehicle")
                                .getJSONObject("EXT020");

                // If there are results in the images object
                vehicleImages.add(intImageJSONObject.getString("url"));
                vehicleImages.add(extImageJSONObject.getString("url"));

                return vehicleImages;}else if(specificUrl.equals("components")) {
                List <String> vehicleImages =
                        new ArrayList <>();
                JSONObject baseJsonResponse =
                        new JSONObject(jsonResponse);
                JSONObject upholsteryImageJSONObject =
                        baseJsonResponse.getJSONObject("components")
                                .getJSONObject("upholstery");
                JSONObject trimImageJSONObject =
                        baseJsonResponse.getJSONObject("components")
                                .getJSONObject("trim");
                JSONObject rimImageJSONObject =
                        baseJsonResponse.getJSONObject("components")
                                .getJSONObject("rim");
                JSONObject engineImageJSONObject =
                        baseJsonResponse.getJSONObject("components")
                                .getJSONObject("engine");

                // If there are results in the images object
                vehicleImages.add(upholsteryImageJSONObject.getString("url"));
                vehicleImages.add(trimImageJSONObject.getString("url"));
                vehicleImages.add(rimImageJSONObject.getString("url"));
                vehicleImages.add(engineImageJSONObject.getString("url"));
                return vehicleImages;}}catch(JSONException e) {
            Log.e(TAG, "Problem parsing the " +
                    "Mercedes JSON results", e);}return null;}
     // Get json from url
    // by making HTTP GET mehtod
    private static String
    makeHttpRequest(URL url)
            throws IOException {
        String jsonResponse = "";
        if (url != null) {
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            // Making HTTP request
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                }}catch(IOException e) {
                // Handle the exception
                assert urlConnection != null;
                Log.e(TAG, "Status code " +
                        urlConnection.getResponseCode(), e);} finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();}
                if (inputStream != null) {
                    inputStream.close();}}}return jsonResponse;}
    // Read data from inputStream
    private static String
    readFromStream(InputStream inputStream)
            throws IOException {
        // StringBuilder use to stores the objects in heap
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            // to read non-char based which is the images
            InputStreamReader inputStreamReader =
                    new InputStreamReader(
                            inputStream,
                            Charset.forName("UTF-8"));
            // Read json line by line
            BufferedReader reader =
                    new BufferedReader(
                            inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                // append to stringBuilder object
                output.append(line);
                line = reader.readLine();}}
        return output.toString();}}
