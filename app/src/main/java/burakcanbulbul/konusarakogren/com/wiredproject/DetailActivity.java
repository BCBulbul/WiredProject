package burakcanbulbul.konusarakogren.com.wiredproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import Constants.Constants;
import Models.Article;

public class DetailActivity extends AppCompatActivity implements Serializable
{
    /*
    Makalelerin detaylarının görülmesini sağlayan aktivite. Yazar ve Makale Türü Bilgisi, Makale ve Resmini içeriyor.
    Makalenin üzerine long click yapıldığında makalede en sık geçen beş İngilizce kelimenin Türkçe karşılığını
     döndüren aktiviteye geçiyor.
     */
    private Intent articleIntent;
    private Article article;
    private String articleLink;
    private TextView articleAuthorView;
    private TextView articleGenreView;
    private TextView articleView;
    private ImageView articleImageView;
    private Document document;
    private Elements articleElements;
    private String code;
    private int position;
    private String articleText;
    private String [] words;
    private HashSet<String> oneWords;
    private HashMap<Integer,String> wordMap;
    private HashSet<Integer> counterArray;
    private ArrayList<Integer> counterArraySize;
    private ArrayList<String> fiveWordsList;
    private Intent passWords;

    public DetailActivity()
    {


    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public ArrayList<String> getFiveWordsList() {
        return fiveWordsList;
    }

    public void setFiveWordsList(ArrayList<String> fiveWordsList) {
        this.fiveWordsList = fiveWordsList;
    }

    private class ArticleInformationAsyncTask extends AsyncTask<Void,Void,Article>
    {
        @Override
        protected Article doInBackground(Void... voids)
        {
            try
            {
                Article article = getArticleData();
                splitForeignWords(article.getArticle());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return article;
        }

        @Override
        protected void onPostExecute(Article article)
        {
            super.onPostExecute(article);
            Picasso.with(DetailActivity.this).load(article.getImageLink()).into(articleImageView);
            articleAuthorView.setText("Author : "+article.getArticleAuthor());
            articleGenreView.setText("Genre : "+article.getArticleGenre());
            articleView.setText(article.getArticle());
            articleView.setMovementMethod(new ScrollingMovementMethod());
        }
    }
    private Article getArticleData() throws IOException
    {
        articleIntent = getIntent();
        article = (Article) articleIntent.getSerializableExtra("selectedArticle");
        position = articleIntent.getIntExtra("position",0);
        Log.d("Position : ", String.valueOf(position));
        switch (position)
        {
            case 0:
                code=Constants.WIRED_FIRST_ARTICLE;
                break;
            case 1:
                code=Constants.WIRED_SECOND_ARTICLE;
                break;

            case 2:
                code=Constants.WIRED_THIRD_ARTICLE;
                break;

            case 3:
                code=Constants.WIRED_FOURTH_ARTICLE;
                break;

            case 4:
                code=Constants.WIRED_FIFTH_ARTICLE;
                break;

            default:
                break;

        }
                    String mergedLink = Constants.WIRED_URL + article.getHrefLink();
                    document = Jsoup.connect(mergedLink).get();
                    articleElements = document.select(code);
                    article.setArticle(articleElements.select(Constants.HTML_PARAGRAPH_CODE).text());


        return article;
    }

    private void init()
    {
        articleAuthorView = (TextView) findViewById(R.id.articleAuthor);
        articleGenreView = (TextView) findViewById(R.id.articleGenre);
        articleView = (TextView) findViewById(R.id.article);
        articleImageView = (ImageView) findViewById(R.id.articleImage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        new ArticleInformationAsyncTask().execute();
        registerForContextMenu(articleView);


    }

    private void splitForeignWords(String article)
    {
        words = article.split(" ");
        int counter = 0;
        oneWords = new HashSet<>();
        counterArray = new HashSet<>();
        counterArraySize = new ArrayList<>();
        wordMap = new HashMap<>();
        fiveWordsList = new ArrayList<>();

        for(String word : words)
        {
            oneWords.add(word);
        }

        for(String word:oneWords)
        {
            counter = 0;
            for(int j=0;j<words.length;j++)
            {
                if(word.equals(words[j]))
                {
                    counter++;
                }
            }

            wordMap.put(counter,word);
            counterArray.add(counter);
        }

        for(Integer i : counterArray)
        {

            counterArraySize.add(i);
        }


        Collections.sort(counterArraySize);

        counter = 0;
        for(int k=counterArraySize.size()-1;k>=0;k--)
        {

            if(counter < 5)
            {
                getFiveWordsList().add(wordMap.get(counterArraySize.get(k)));
            }

            counter++;

        }

        setFiveWordsList(fiveWordsList);



    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("En Sık Geçen İngilizce Kelimeler");
        menu.add(0, v.getId(), 0, "Göster");
        menu.add(0, v.getId(), 0, "Gösterme");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        if(item.getTitle()=="Göster")
        {

            passWords = new Intent(DetailActivity.this,WordsActivity.class);
            passWords.putExtra("englishArrayList",getFiveWordsList());
            startActivity(passWords);

        }
        else if (item.getTitle()=="Gösterme")
        {

        }

        else {return false;}

        return true;
    }



    public String getArticleLink()
    {
        return articleLink;
    }

    public void setArticleLink(String articleLink)
    {
        this.articleLink = articleLink;
    }
}
