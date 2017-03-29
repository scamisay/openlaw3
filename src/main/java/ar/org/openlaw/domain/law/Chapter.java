package ar.org.openlaw.domain.law;

import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scamisay on 18/02/17.
 */
public class Chapter {

    private String title;

    private List<Article> articles = new ArrayList<>();

    @Transient
    private String chunk = "";

    public Chapter(String title) {
        this.title = title;
    }

    public Chapter(VersionedChapter versionedChapter) {
        this.title = versionedChapter.getTitle();
    }

    public String getTitle() {
        return title;
    }

    public List<Article> getArticles() {
        return articles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chapter chapter = (Chapter) o;

        if (!title.equals(chapter.title)) return false;
        return articles != null ? articles.equals(chapter.articles) : chapter.articles == null;

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (articles != null ? articles.hashCode() : 0);
        return result;
    }

    public void addArticle(String articleContent){
        if(articles == null){
            articles = new ArrayList<>();
        }
        if(articleContent != null && !articleContent.isEmpty()){
            articles.add(new Article(articleContent));
        }
    }

    public void addArticle(Article anArticle){
        if(articles == null){
            articles = new ArrayList<>();
        }
        if(anArticle != null){
            articles.add(anArticle);
        }
    }

    public String getChunk() {
        return chunk;
    }

    public void setChunk(String chunk) {
        this.chunk = chunk;
    }

    public void addArticles(List<Article> articles) {
        for(Article anArticle : articles){
            addArticle(anArticle);
        }
    }
}
