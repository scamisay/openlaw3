package ar.org.openlaw.domain.law;

/**
 * Created by scamisay on 12/03/17.
 */
public abstract class VersionedElement {

    protected Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public static VersionedElement parse(Object anElement) {
        if(anElement == null){
            return null;
        }
        if(anElement instanceof Article){
            Article anArticle = (Article)anElement;
            return new VersionedArticle(anArticle.getTitle(), anArticle.getContent());
        }else if(anElement instanceof Chapter){
            Chapter aChapter = (Chapter) anElement;
            return new VersionedChapter(aChapter.getTitle());
        }
        return null;
    }
}
