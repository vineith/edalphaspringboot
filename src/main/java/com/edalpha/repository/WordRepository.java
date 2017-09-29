package com.edalpha.repository;

import com.edalpha.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by kaul on 9/23/17.
 */

public interface WordRepository extends JpaRepository<Word, Long>, WordRepositoryCustom {

    List<Word> findByWord(String word);



}
