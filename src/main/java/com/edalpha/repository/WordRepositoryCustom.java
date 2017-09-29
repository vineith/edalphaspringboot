package com.edalpha.repository;

import com.edalpha.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by kaul on 9/24/17.
 */
public interface WordRepositoryCustom {

    List<Word> getWordBySynonymIds(String word);
}
