package burakcanbulbul.konusarakogren.com.wiredproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import Adapters.ArticleAdapter;
import Constants.Constants;
import Models.Article;

/**
 * Created by Burak Can BÜLBÜL on 24.09.2017.
 */

public class ArticleAsyncTask extends AsyncTask<Void,Void,ArrayList<Article>> implements Serializable
{
   /*
       Wired.com üzerinden makalelerin parse edilmesini sağlayan sınıf.
    */

    private ArrayList<Article> articleList;
    private ArticleInformations articleInformations;
    private ArticleAdapter articleAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Activity activity;
    private Intent intent;

    public ArticleAsyncTask(Activity activity)
    {
        this.activity = activity;

    }

    @Override
    protected void onPostExecute(ArrayList<Article> articles)
    {

        super.onPostExecute(articles);
        mRecyclerView = (RecyclerView) activity.findViewById(R.id.recycler_view);
        if (mRecyclerView != null)
        {
            mRecyclerView.setHasFixedSize(true);
        }
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        articleAdapter = new ArticleAdapter(articleList,activity);
        mRecyclerView.setAdapter(articleAdapter);
        articleAdapter.notifyDataSetChanged();

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(activity, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position)
            {

                intent = new Intent(activity,DetailActivity.class);
                Article getArticleAtPosition = articleList.get(position);
                intent.putExtra("selectedArticle", (Serializable) getArticleAtPosition);
                intent.putExtra("position",position);
                activity.startActivity(intent);




            }
        }));


    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<Article> doInBackground(Void... voids)
    {
        articleInformations = ArticleInformations.getInstance();
        articleList = new ArrayList<Article>();

        try
        {
            articleList.add(articleInformations.getFirstArticleInformations());
            articleList.add(articleInformations.getSecondArticleInformations());
            articleList.add(articleInformations.getThirdArticleInformations());
            articleList.add(articleInformations.getFourthArticleInformations());
            articleList.add(articleInformations.getFifthArticleInformations());

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return articleList;
    }
}
