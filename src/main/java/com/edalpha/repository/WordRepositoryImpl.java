package com.edalpha.repository;

import com.edalpha.model.Word;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by kaul on 9/24/17.
 */
public class WordRepositoryImpl implements WordRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    WordRepository wordRepository;


    @Override
    public List<Word> getWordBySynonymIds(String word) {

         Query query = entityManager.createNativeQuery("SELECT wd.* FROM edalpha_service.word as wd where wd.word =?1", Word.class);
        query.setParameter(1, word);
        List<Word> words = query.getResultList();
        Query query1 = entityManager.createNativeQuery("SELECT wd.* from edalpha_service.word as wd where syn_id=?1",Word.class);
        query1.setParameter(1, words.get(0).getSynId());
        return query1.getResultList();
    }


}
