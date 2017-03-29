package ar.org.openlaw.domain.law;

import ar.org.openlaw.domain.BaseDomain;

/**
 * Created by scamisay on 19/02/17.
 */
public class Article {

    private String title;
    private String content;

    private Article(){}

    public Article(String content) {
        BaseDomain.checkNotEmpty(content);
        this.content = content;
    }

    public Article(String title, String content) {
        BaseDomain.checkNotEmpty(content, title);
        this.content = content;
        this.title = title;
    }

    public Article(VersionedArticle anArticle) {
        BaseDomain.checkNotNull(anArticle);
        this.content = anArticle.getContent();
        this.title = anArticle.getTitle();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (!content.equals(article.content)) return false;
        return title != null ? title.equals(article.title) : article.title == null;

    }

    @Override
    public int hashCode() {
        int result = content.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }
}
