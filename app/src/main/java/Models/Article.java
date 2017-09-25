package Models;

import java.io.Serializable;

/**
 * Created by burty on 24.09.2017.
 */

public class Article implements Serializable
{

    private String article;
    private String imageLink;
    private String hrefLink;
    private String articleTitle;
    private String articleAuthor;
    private String articleGenre;

    public Article(String article, String imageLink, String hrefLink, String articleTitle, String articleAuthor)
    {

        this.article = article;
        this.imageLink = imageLink;
        this.hrefLink = hrefLink;
        this.articleTitle = articleTitle;
        this.articleAuthor = articleAuthor;
    }

    public Article()
    {

    }

    public String getArticle()
    {
        return article;
    }

    public void setArticle(String article)
    {
        this.article = article;
    }

    public String getImageLink()
    {
        return imageLink;
    }

    public void setImageLink(String imageLink)
    {
        this.imageLink = imageLink;
    }

    public String getHrefLink()
    {
        return hrefLink;
    }

    public void setHrefLink(String hrefLink)
    {
        this.hrefLink = hrefLink;
    }

    public String getArticleTitle()
    {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle)
    {
        this.articleTitle = articleTitle;
    }

    public String getArticleAuthor()
    {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor)
    {
        this.articleAuthor = articleAuthor;
    }

    public String getArticleGenre() {
        return articleGenre;
    }

    public void setArticleGenre(String articleGenre) {
        this.articleGenre = articleGenre;
    }
}
