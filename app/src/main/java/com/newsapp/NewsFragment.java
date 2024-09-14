package com.newsapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private ArrayList<ModelNewsItem> newsArticlesList;
    private RequestQueue requestQueue;
    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_news, container, false);
        progressDialog = new ProgressDialog(getContext());

        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Loading the news");
        progressDialog.show();

        recyclerView = view.findViewById(R.id.recyclerviewNewsList);
        newsArticlesList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(getContext());

        fetchNewsArticles();


        return view;
    }

    private void fetchNewsArticles() {
        String newsArticlesUrl =
                "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=64d7e522428f47c8aa8b34701057a8fb";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, newsArticlesUrl, null, response -> {


            try {

                JSONArray array = response.getJSONArray("articles");

                for (int i = 0; i < array.length(); i++) {

                    JSONObject jsonObject = array.getJSONObject(i);
                    JSONObject sourceObject = jsonObject.getJSONObject("source");
                    Log.d("NewsArticles", "fetchNewsArticles: " + array);

                    String articleTitle = jsonObject.getString("title");
                    String articleDesc = jsonObject.getString("description");
                    String articleSource = sourceObject.getString("name");
                    String articleThumbnail = jsonObject.getString("urlToImage");
                    String articleUrl = jsonObject.getString("url");
                    String articlePublishedDate = jsonObject.getString("publishedAt");
                    String articleAuthor = jsonObject.getString("author");
                    String articleContent = jsonObject.getString("content");

                    newsArticlesList.add(new ModelNewsItem(articleTitle, articleSource, articleThumbnail, articleDesc, articleUrl, articlePublishedDate, articleContent, articleAuthor));


                }
                newsAdapter = new NewsAdapter(getContext(), newsArticlesList);
                recyclerView.setAdapter(newsAdapter);
                progressDialog.dismiss();

            } catch (Exception e) {
                e.printStackTrace();

            }


        }, error -> {
            Toast.makeText(getContext(), "Error" + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("User-Agent", "Mozilla/5.0");
                return headers;
            }
        };

        requestQueue.add(jsonObjectRequest);


    }
}