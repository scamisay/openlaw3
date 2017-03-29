package ar.org.openlaw.domain.law;

import ar.org.openlaw.domain.BaseDomain;

/**
 * Created by scamisay on 18/02/17.
 */
public class LawInformation {

    private String title;
    private String number;

    public LawInformation(String title, String number) {
        BaseDomain.checkNotNull(title,number);
        this.title = title;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LawInformation that = (LawInformation) o;

        if (!title.equals(that.title)) return false;
        return number.equals(that.number);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + number.hashCode();
        return result;
    }

    public String getTitle() {
        return title;
    }

    public String getNumber() {
        return number;
    }
}
