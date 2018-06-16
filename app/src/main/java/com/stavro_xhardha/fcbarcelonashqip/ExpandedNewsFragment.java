package com.stavro_xhardha.fcbarcelonashqip;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain;
import com.stavro_xhardha.fcbarcelonashqip.events.CheckNetworkEvent;
import com.stavro_xhardha.fcbarcelonashqip.model.NewsBody;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ExpandedNewsFragment extends Fragment {

    private TextView newsBody;
    private ImageView newsImage;

    public ExpandedNewsFragment() {
    }

    public static ExpandedNewsFragment newInstance() {
        return new ExpandedNewsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_expanded_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsBody = view.findViewById(R.id.news_body);
        newsImage = view.findViewById(R.id.imageViewCollapsing);
        if (Brain.isNetworkAvailable(getActivity())){
            Picasso.get()
                    .load(Brain.NEWS_IMAGE_URL + Brain.getImageEndpoint())
                    .into(newsImage);
            new GetExpandedNewsTask().execute(Brain.NEWS_BODY + Brain.getNewsId());
        }else{
            EventBus.getDefault().post(new CheckNetworkEvent());
        }
    }

    class GetExpandedNewsTask extends AsyncTask<String, String, String> {
        private NewsBody newsBodyResponce;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                OkHttpClient client = new OkHttpClient();
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                Request request = new Request.Builder()
                        .url(strings[0])
                        .build();
                InputStream mInputStream = null;
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    mInputStream = response.body().byteStream();
                }
                if (mInputStream != null) {
                    Reader reader = new InputStreamReader(mInputStream);
                    Type responseType = new TypeToken<NewsBody>() {
                    }.getType();

                    newsBodyResponce = gson.fromJson(reader, responseType);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (newsBodyResponce != null) {
                String newsBodyResponseString = newsBodyResponce.getBody();
                newsBody.setText(newsBodyResponseString);
            } else {
                Snackbar.make(getView() , getResources().getString(R.string.can_not_get_data) , Snackbar.LENGTH_LONG ).show();
            }
        }
    }

}
