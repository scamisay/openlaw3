package ar.org.openlaw.domain.law;

import ar.org.openlaw.domain.BaseDomain;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

/**
 * Created by scamisay on 18/02/17.
 */
@Document(collection = "laws")
public class Law extends BaseDomain {

    private LawInformation information;

    private List<List<VersionedElement>> versionedElements;

    private Integer maxVersion;
    //private String introduction;

    //private List<Chapter> chapters;

    //private String ending;

    public Law(LawInformation information, List<VersionedElement> versionedElements) {
        BaseDomain.checkNotNull(information, versionedElements);
        this.information = information;
        this.versionedElements = Arrays.asList(versionedElements);
    }

    private void initVersionedElements(List<VersionedElement> lawElements){
        BaseDomain.checkNotEmpty(lawElements);
        if(versionedElements == null){
            versionedElements = new ArrayList<>();
        }
        for(VersionedElement firstVersionElement : lawElements){
            firstVersionElement.setVersion(0);
            versionedElements.add(Arrays.asList(firstVersionElement));
        }
        maxVersion = 0;

    }

    /*public void setIntroduction(String introduction) {
        BaseDomain.checkNotEmpty(introduction);
        this.introduction = introduction;
    }*/

    /*public void addChapter(Chapter aChapter){
        BaseDomain.checkNotNull(aChapter);


    }

    private void addFirstChapter(Chapter aChapter) {
        if(introduction != null && aChapter.getTitle().isEmpty()
                &&  !aChapter.getArticles().isEmpty()
                && aChapter.getArticles().get(0).getTitle().isEmpty()){
            setIntroduction(aChapter.getArticles().get(0).getContent());
            aChapter.getArticles().remove(0);
        }
        chapters.add(aChapter);
    }

    public void setEnding(String ending) {
        BaseDomain.checkNotEmpty(introduction);
        this.ending = ending;
    }*/

    public LawInformation getInformation() {
        return information;
    }

    public List<Chapter> getChapters(Integer version) {
        return buildChapters(version);
    }

    private List<Chapter> buildChapters(Integer version) {
        if(versionedElements.size() >= version){
            List<Chapter> chapters = new ArrayList<>();
            ListIterator<VersionedElement> it = ((List<VersionedElement>)versionedElements.get(version).get(0)).listIterator();
            while (it.hasNext()){
                VersionedElement aLawElement = it.next();
                if(aLawElement instanceof VersionedChapter){
                    Chapter aChapter = new Chapter((VersionedChapter) aLawElement);
                    while (it.hasNext()){
                        aLawElement = it.next();
                        if(aLawElement instanceof VersionedArticle){
                            Article anArticle = new Article((VersionedArticle) aLawElement);
                            aChapter.addArticle(anArticle);
                        }else {
                            it.previous();
                            break;
                        }
                    }
                    chapters.add(aChapter);
                }
            }
            return chapters;
        }else{
            return new ArrayList<>();
        }
    }

    public String getIntroduction() {
        return "";
    }

    public String getEnding() {
        return "";
    }
}
