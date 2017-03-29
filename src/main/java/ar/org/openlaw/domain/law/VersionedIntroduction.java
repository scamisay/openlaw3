package ar.org.openlaw.domain.law;

/**
 * Created by scamisay on 12/03/17.
 */
public class VersionedIntroduction extends VersionedElement {
    private String content;

    public VersionedIntroduction(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
