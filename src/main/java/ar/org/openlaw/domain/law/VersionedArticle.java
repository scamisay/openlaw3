package ar.org.openlaw.domain.law;

import ar.org.openlaw.domain.BaseDomain;

/**
 * Created by scamisay on 12/03/17.
 */
public class VersionedArticle extends VersionedElement {
    private String title;
    private String content;

    private VersionedArticle() {
    }

    public VersionedArticle(String content) {
        BaseDomain.checkNotEmpty(content);
        this.content = content;
    }

    public VersionedArticle(String title, String content) {
        BaseDomain.checkNotEmpty(content, title);
        this.content = content;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VersionedArticle article = (VersionedArticle) o;

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
