package ar.org.openlaw.domain;

import org.springframework.data.annotation.Id;

import java.util.Collection;

/**
 * Created by scamisay on 18/02/17.
 */
public class BaseDomain{

    @Id
    protected String id;

    public String getId() {
        return id;
    }

    public static void checkNotNull(Object ... args){
        for(Object obj : args){
            if(obj == null){
                throw new RuntimeException("All fields are mandatory");
            }
        }
    }

    public static void checkNotEmpty(String ... args){
        checkNotNull(args);
        for(String aString : args){
            if(aString.isEmpty()){
                throw new RuntimeException("Fields cannot be empty");
            }
        }
    }

    public static void checkNotEmpty(Collection... args){
        checkNotNull(args);
        for(Collection aCollection : args){
            if(aCollection.isEmpty()){
                throw new RuntimeException("Fields cannot be empty");
            }
        }
    }
}
