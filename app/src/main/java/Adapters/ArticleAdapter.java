package Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import Models.Article;
import burakcanbulbul.konusarakogren.com.wiredproject.MainActivity;
import burakcanbulbul.konusarakogren.com.wiredproject.R;

/**
 * Created by Burak Can BÜLBÜL on 24.09.2017.
 */

    public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder>
{

        private List<Article> articleList;
        private Context context;
        public static class ViewHolder extends RecyclerView.ViewHolder
        {
            public TextView tvArticleTitle;
            public TextView tvAuthor;
            public TextView tvGenre;
            public ImageView ivArticleImage;

            public ViewHolder(View v)
            {
                super(v);
                tvArticleTitle = (TextView) v.findViewById(R.id.tv_article_title);
                tvAuthor = (TextView) v.findViewById(R.id.tv_author);
                tvGenre = (TextView) v.findViewById(R.id.tv_genre);
                ivArticleImage = (ImageView) v.findViewById(R.id.iv_article_image);
            }
        }


        public ArticleAdapter(List<Article> articleList, Context context)
        {
            this.articleList = articleList;
            this.context = context;
        }

        @Override
        public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {


            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item,parent,false);


            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ArticleAdapter.ViewHolder holder, int position)
        {

            Article article = articleList.get(position);
            Picasso.with(context).load(article.getImageLink()).into(holder.ivArticleImage);
            holder.tvArticleTitle.setText(article.getArticleTitle());
            holder.tvAuthor.setText(article.getArticleAuthor());
            holder.tvGenre.setText(article.getArticleGenre());


        }

        @Override
        public int getItemCount() {
            return articleList.size();
        }

}


