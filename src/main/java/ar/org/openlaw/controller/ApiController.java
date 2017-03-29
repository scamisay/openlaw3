package ar.org.openlaw.controller;

import ar.org.openlaw.domain.law.Law;
import ar.org.openlaw.service.LawService;
import ar.org.openlaw.service.compiler.CompilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by scamisay on 02/11/16.
 */
@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    private CompilerService lawCompiler;

    @Autowired
    private LawService lawService;

    @RequestMapping(value = "saveLaw", method = RequestMethod.POST)
    public String save(@RequestParam(value = "title") String title,
                       @RequestParam(value = "number") String number,
                       @RequestParam(value = "content") String lawContent){
        try{
            Law law = lawCompiler.parseFromChunk(title, number, lawContent);
            lawService.save(law);
        }catch (Exception e){

        }
        return "created";
    }

    /*String chapters [] = lawContent.split("CAPITULO.*\n");
//controlar caso chapters vacio

    chapters[2].split("Artículo")*/
}
