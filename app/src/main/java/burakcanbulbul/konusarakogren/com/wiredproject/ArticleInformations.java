package burakcanbulbul.konusarakogren.com.wiredproject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import Constants.Constants;
import Models.Article;

/**
 * Created by Burak Can BÜLBÜL on 24.09.2017.
 */

public class ArticleInformations
{
    /*
    Parse Edilmiş Makaleleri Article Objesine dolduran sınıf.
     */
    private static ArticleInformations articleInformations;
    private Document document;
    private Elements articleElements;
    private Elements articleImageElements;
    private Article article;

    public static synchronized ArticleInformations getInstance()
    {
        if(articleInformations == null)
        {
            articleInformations = new ArticleInformations();
        }

        return articleInformations;
    }

    public Article getFirstArticleInformations() throws IOException
    {
        document = Jsoup.connect(Constants.WIRED_URL).get();
        articleElements = document.select(Constants.WIRED_FIRST_ARTICLE_HTML_CODE);
        articleImageElements = document.select(Constants.WIRED_FIRST_ARTICLE_IMAGE_HTML_CODE);
        article = new Article();
        article.setArticleTitle(articleElements.select(Constants.HTML_HREF_CODE).select(Constants.HTML_H_CODE).text());
        article.setArticleGenre(articleElements.select(Constants.WIRED_FIRST_ARTICLE_GENRE_HTML_CODE)
                .select(Constants.WIRED_FIRST_ARTICLE_GENRE_SPAN_CODE).text());
        article.setArticleAuthor(articleElements.select(Constants.WIRED_FIRST_ARTICLE_AUTHOR_HTML_CODE).
                select(Constants.HTML_HREF_CODE).text());
        article.setImageLink(articleImageElements.select(Constants.WIRED_ARTICLE_INNER_IMAGE_HTML_CODE).
                select(Constants.HTML_IMAGE_CODE).attr(Constants.HTML_IMAGE_SOURCE_ATTR_CODE));
        article.setHrefLink(articleElements.select(Constants.HTML_HREF_CODE).attr(Constants.HTML_HREF_ATTR_CODE));
        article.setArticle(articleElements.select(Constants.WIRED_FIRST_ARTICLE).select(Constants.HTML_PARAGRAPH_CODE).text());


        return article;
    }

    public Article getSecondArticleInformations() throws IOException
    {

        document = Jsoup.connect(Constants.WIRED_URL).get();
        articleElements = document.select(Constants.WIRED_SECOND_ARTICLE_HTML_CODE);
        articleImageElements = document.select(Constants.WIRED_SECOND_ARTICLE_IMAGE_HTML_CODE);
        article = new Article();
        article.setHrefLink(articleElements.select(Constants.HTML_HREF_CODE).attr(Constants.HTML_HREF_ATTR_CODE));
        article.setArticleGenre(articleElements.select(Constants.WIRED_SECOND_ARTICLE_GENRE_HTML_CODE).text());
        article.setArticleTitle(articleElements.select(Constants.HTML_H_CODE).text());
        article.setImageLink(articleImageElements.select(Constants.WIRED_ARTICLE_INNER_IMAGE_HTML_CODE).
                select(Constants.HTML_IMAGE_CODE).attr(Constants.HTML_IMAGE_SOURCE_ATTR_CODE));
        article.setArticleAuthor(articleElements.select(Constants.WIRED_SECOND_ARTICLE_AUTHOR_HTML_CODE).text());


        return article;
    }

    public Article getThirdArticleInformations() throws IOException
    {
        document = Jsoup.connect(Constants.WIRED_URL).get();
        articleElements = document.select(Constants.WIRED_THIRD_ARTICLE_HTML_CODE);
        articleImageElements = document.select(Constants.WIRED_THIRD_ARTICLE_IMAGE_HTML_CODE);
        article = new Article();
        article.setHrefLink(articleElements.select(Constants.HTML_HREF_CODE).attr(Constants.HTML_HREF_ATTR_CODE));
        article.setArticleGenre(articleElements.select(Constants.WIRED_THIRD_ARTICLE_GENRE_HTML_CODE).text());
        article.setArticleTitle(articleElements.select(Constants.HTML_HREF_CODE).select(Constants.HTML_H_CODE).text());
        article.setImageLink(articleImageElements.select(Constants.WIRED_ARTICLE_INNER_IMAGE_HTML_CODE).
                select(Constants.HTML_IMAGE_CODE).attr(Constants.HTML_IMAGE_SOURCE_ATTR_CODE));
        article.setArticleAuthor(articleElements.select(Constants.WIRED_THIRD_ARTICLE_AUTHOR_HTML_CODE)
                .select(Constants.HTML_HREF_CODE).text());

        return article;

    }

    public Article getFourthArticleInformations() throws IOException
    {
        document = Jsoup.connect(Constants.WIRED_URL).get();
        articleElements = document.select(Constants.WIRED_FOURTH_ARTICLE_HTML_CODE);
        articleImageElements = document.select(Constants.WIRED_FOURTH_ARTICLE_IMAGE_HTML_CODE);
        article = new Article();
        article.setHrefLink(articleElements.select(Constants.HTML_HREF_CODE).attr(Constants.HTML_HREF_ATTR_CODE));
        article.setArticleTitle(articleElements.select(Constants.HTML_HREF_CODE).select(Constants.HTML_H_CODE).text());
        article.setArticleGenre(articleElements.select(Constants.WIRED_FOURTH_ARTICLE_GENRE_HTML_CODE).text());
        article.setImageLink(articleImageElements.select(Constants.WIRED_ARTICLE_INNER_IMAGE_HTML_CODE).
                select(Constants.HTML_IMAGE_CODE).attr(Constants.HTML_IMAGE_SOURCE_ATTR_CODE));
        article.setArticleAuthor(articleElements.select(Constants.WIRED_FOURTH_ARTICLE_AUTHOR_HTML_CODE).
                select(Constants.HTML_HREF_CODE).text());
        return article;

    }
    public Article getFifthArticleInformations() throws IOException
    {
        document = Jsoup.connect(Constants.WIRED_URL).get();
        articleElements = document.select(Constants.WIRED_FIFTH_ARTICLE_HTML_CODE);
        articleImageElements = document.select(Constants.WIRED_FIFTH_ARTICLE_IMAGE_HTML_CODE);
        article = new Article();
        article.setImageLink(articleImageElements.select(Constants.WIRED_ARTICLE_INNER_IMAGE_HTML_CODE)
                .select(Constants.HTML_IMAGE_CODE).attr(Constants.HTML_IMAGE_SOURCE_ATTR_CODE));
        article.setHrefLink(articleElements.select(Constants.HTML_HREF_CODE).attr(Constants.HTML_HREF_ATTR_CODE));
        article.setArticleAuthor(articleElements.select(Constants.WIRED_FIFTH_ARTICLE_AUTHOR_HTML_CODE).
                select(Constants.HTML_HREF_CODE).text());
        article.setArticleTitle(articleElements.select(Constants.HTML_HREF_CODE).select(Constants.HTML_H_CODE).text());
        article.setArticleGenre(articleElements.select(Constants.WIRED_FIFTH_ARTICLE_GENRE_HTML_CODE).text());

        return article;
    }
}
