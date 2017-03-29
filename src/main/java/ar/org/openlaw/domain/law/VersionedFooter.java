package ar.org.openlaw.domain.law;

/**
 * Created by scamisay on 12/03/17.
 */
public class VersionedFooter extends VersionedElement {
    private String content;

    public VersionedFooter(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
