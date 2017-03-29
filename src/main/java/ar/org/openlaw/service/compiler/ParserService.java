package ar.org.openlaw.service.compiler;

import ar.org.openlaw.domain.law.Article;
import ar.org.openlaw.domain.law.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by scamisay on 20/02/17.
 */
@Service
public class ParserService {

    @Autowired
    private LexAnalyzerService lexer;


    public List<Chapter> parseChapters(List<Map<String, String>> chapterChunks) {
        List<Chapter> chapters = new ArrayList<>();
        for(Map<String,String> aChunk : chapterChunks){
            Map.Entry<String,String> entry=aChunk.entrySet().iterator().next();
            String title = entry.getKey();
            String articleChunk = entry.getValue();
            Chapter aChapter = new Chapter(title);
            aChapter.setChunk(articleChunk);
            chapters.add(aChapter);
        }
        return chapters;
    }

    public List<Article> parseArticlesFromMap(List<Map<String, String>> articleChunks) {
        List<Article> articles = new ArrayList<>();
        if(articleChunks == null){
            return articles;
        }
        for(Map<String,String> aChunk : articleChunks){
            Map.Entry<String,String> entry = aChunk.entrySet().iterator().next();
            String title = entry.getKey();
            String articleBody = entry.getValue();
            if(title.isEmpty()){
                continue;
            }
            Article anArticle = new Article(title, articleBody);
            articles.add(anArticle);
        }
        return articles;
    }

    public List<Chapter> parseArticles(List<Chapter> chapters) {
        String tokenForArticles = lexer.findValidTokenForArticles(chapters);
        if(tokenForArticles == null){
            return null;
        }

        for(Chapter aChapter : chapters){
            List<Article> articles = parseListOfArticles(aChapter.getChunk(), tokenForArticles);
            if(!articles.isEmpty()){
                aChapter.addArticles(articles);
            }
        }
        return chapters;
    }

    private List<Article> parseListOfArticles(String chunk, String tokenForArticles) {
        return parseArticlesFromMap(lexer.lexArticles(chunk, tokenForArticles));
    }
}
