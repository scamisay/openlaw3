package ar.org.openlaw.service.compiler;

import ar.org.openlaw.domain.law.Chapter;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by scamisay on 19/02/17.
 */
@Service
public class LexAnalyzerService {

    private static final String TOKEN_BEGINING = "\n";

    private List<String> CHAPTER_TOKENS = Arrays.asList(TOKEN_BEGINING+"Capítulo", TOKEN_BEGINING+"Capitulo", TOKEN_BEGINING+"CAPÍTULO", TOKEN_BEGINING+"CAPITULO");

    private List<String> ARTICLE_TOKENS = Arrays.asList(TOKEN_BEGINING+"Artículo", TOKEN_BEGINING+"Articulo", TOKEN_BEGINING+"ARTÍCULO", TOKEN_BEGINING+"ARTICULO");

    private String[] divisionByUnits(String chunk, List<String> listOfTokens) {
        String[] units= null;
        for(String token : listOfTokens){
            units = chunk.split(token);
            if(units.length > 1){
                //con este token esta escrito el separador de unidades!
                break;
            }
        }
        if(units.length == 1){
            return null;
        }else{
            return units;
        }
    }

    private List<Map<String,String>> divisionInsideUnits(String[] chunks){
        if(chunks == null){
            return null;
        }
        List<Map<String,String>> units = new ArrayList<>();
        for(String unitChunk : chunks){
            int indexToCut = unitChunk.indexOf("\n");
            if(indexToCut == -1){
                continue;
            }
            String first = unitChunk.substring(0,indexToCut);
            String second = unitChunk.substring(indexToCut);
            Map<String,String> unit = new HashMap<>();
            unit.put(first, second);
            units.add(unit);
        }
        if(units.isEmpty()){
            return null;
        }else {
            return units;
        }

    }

    public String[] divisionByChapters(String chunk) {
        String[] array = divisionByUnits(chunk, CHAPTER_TOKENS);
        if(array == null){
            return chunk.split(CHAPTER_TOKENS.get(0));
        }else {
            return array;
        }
    }

    public String[] divisionByArticles(String chunk, String token) {
        return divisionByUnits(chunk, Arrays.asList(token));
    }

    public List<Map<String,String>> lexChapters(String chunk){
        return divisionInsideUnits(divisionByChapters(chunk));
    }

    public List<Map<String,String>> lexArticles(String chunk, String token){
        return divisionInsideUnits(divisionByArticles(chunk, token));
    }

    public String findValidTokenForArticles(List<Chapter> chapters) {
        for(String token : ARTICLE_TOKENS){
            for(Chapter aChapter : chapters){
                if(containsToken(aChapter.getChunk(), token)){
                    return token;
                }
            }
        }
        return null;
    }

    private boolean containsToken(String aString, String token){
        return aString.split(token).length > 1;
    }
}
