package ar.org.openlaw.service.compiler;

import ar.org.openlaw.domain.law.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by scamisay on 18/02/17.
 */
@Service
public class CompilerService {

    @Autowired
    private LexAnalyzerService lexer;

    @Autowired
    private ParserService parser;

    public Law parseFromChunk(String title, String number, String chunk){
        List<Map<String,String>> chaptersTokenized = lexer.lexChapters(chunk);
        List<Chapter> chapters = parser.parseChapters(chaptersTokenized);

        List<Chapter> chaptersWithArticles = parser.parseArticles(chapters);

        return buildLaw(title, number, chaptersWithArticles);
    }

    private Law buildLaw(String title, String number, List<Chapter> chaptersWithArticles) {
        Iterator<Chapter> chapterIterator = chaptersWithArticles.iterator();
/*
        if(!chapterIterator.hasNext()){
            return law;
        }else {
            Chapter firstChapter = chapterIterator.next();
            law.setIntroduction(firstChapter.getChunk());
            while (chapterIterator.hasNext()){
                Chapter chapter = chapterIterator.next();
                law.addChapter(chapter);
            }
        }
*/

        List<VersionedElement> elements = new ArrayList<>();
        while (chapterIterator.hasNext()){
            Chapter chapter = chapterIterator.next();
            elements.add(VersionedElement.parse(chapter));
            for(Article anArticle : chapter.getArticles()){
                elements.add(VersionedElement.parse(anArticle));
            }
        }
        return new Law(new LawInformation(title,number), elements);
    }



    /*String chapters [] = lawContent.split("CAPITULO.*\n");
//controlar caso chapters vacio

    chapters[2].split("Artículo")*/
}
