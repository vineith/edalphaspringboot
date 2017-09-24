package com.edalpha.repository;

import com.edalpha.model.Word;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by kaul on 9/23/17.
 */
public interface WordRepository extends CrudRepository<Word, Long> {

    List<Word> findByWord(String word);

}
