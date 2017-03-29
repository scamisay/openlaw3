package ar.org.openlaw.service.impl;

import ar.org.openlaw.domain.law.Law;
import ar.org.openlaw.repository.LawRepository;
import ar.org.openlaw.service.LawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by scamisay on 05/02/16.
 */
@Service
public class LawServiceImpl implements LawService {

    @Autowired
    private LawRepository lawRepository;

    @Override
    public void save(Law aLaw) {
        lawRepository.save(aLaw);
    }

    @Override
    public List<Law> findAll() {
        return lawRepository.findAll();
    }
}
