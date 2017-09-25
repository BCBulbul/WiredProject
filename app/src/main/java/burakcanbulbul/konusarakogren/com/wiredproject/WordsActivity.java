package burakcanbulbul.konusarakogren.com.wiredproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ExpandedMenuView;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import Constants.Constants;

public class WordsActivity extends AppCompatActivity
{
    /*
    Makalede geçen en sık beş kelimeyi bulan ve bunların Türkçe karşılığını döndüren aktivite.
     */
    private Intent intent;
    private ArrayList<String> englishArrayList;
    private ArrayAdapter adapter;
    private ListView translateView;
    private TextView turkishTranslateView;
    private Document document;
    private Elements elements;
    private String link;
    private  String selectedWord;

    public String getSelectedWord()
    {
        return selectedWord;
    }

    public void setSelectedWord(String selectedWord)
    {
        this.selectedWord = selectedWord;
    }
    private void registerHandlers()
    {
        addOnItemClickListener();
    }

    private String  getTurkishMeans(String word) throws IOException
    {
        document = Jsoup.connect(Constants.CAMBRIDGE_DICTIONARY_URL.concat(word)).get();
        elements = document.select(Constants.CAMBRIDGE_HTML_BODY);
        String text = elements.select(Constants.CAMBRIDGE_HTML_SPAN).text();
        return text;
    }
    private class TranslateAsyncTask extends AsyncTask<String ,Void,String>
    {
        @Override
        protected String doInBackground(String ... voids)
        {


            String text = null;
            try
            {
                text = getTurkishMeans(voids[0]);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return text;
        }

        @Override
        protected void onPostExecute(String aVoid)
        {
            super.onPostExecute(aVoid);
            turkishTranslateView.setText("Anlamı: "+aVoid);
        }
    }

    private void init()
    {
        translateView = (ListView) findViewById(R.id.translateListView);
        turkishTranslateView = (TextView) findViewById(R.id.turkishTranslateView);
        turkishTranslateView.setMovementMethod(new ScrollingMovementMethod());
        bindAdapter();
    }



    private void addOnItemClickListener()
    {
        translateView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i,  long l)
            {

                int position = i;
                new TranslateAsyncTask().execute(englishArrayList.get(position));

            }
        });
    }

    private void bindAdapter()
    {
        intent = getIntent();
        englishArrayList = (ArrayList<String>) intent.getSerializableExtra("englishArrayList");
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,englishArrayList);
        adapter.notifyDataSetChanged();
        translateView.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        init();
        registerHandlers();


    }

}
