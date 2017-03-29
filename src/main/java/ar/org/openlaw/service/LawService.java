package ar.org.openlaw.service;

import ar.org.openlaw.domain.law.Law;

import java.util.List;

/**
 * Created by scamisay on 04/09/16.
 */
public interface LawService {

    void save(Law aLaw);

    List<Law> findAll();
}
