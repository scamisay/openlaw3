package ar.org.openlaw.domain.law;

/**
 * Created by scamisay on 12/03/17.
 */
public class VersionedChapter extends VersionedElement {
    private String title;

    public VersionedChapter(String title) {
        this.title = title;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VersionedChapter that = (VersionedChapter) o;

        return title != null ? title.equals(that.title) : that.title == null;

    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    public String getTitle() {
        return title;
    }
}
