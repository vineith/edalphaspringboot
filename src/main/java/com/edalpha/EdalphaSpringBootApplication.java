package com.edalpha;

import com.edalpha.model.Word;
import com.edalpha.repository.WordRepository;
import com.edalpha.storage.StorageProperties;
import com.edalpha.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class EdalphaSpringBootApplication {

    private static final Logger logger = LoggerFactory.getLogger(EdalphaSpringBootApplication.class);

    @Autowired
    WordRepository wordRepository;

	public static void main(String[] args) {
		SpringApplication.run(EdalphaSpringBootApplication.class, args);
	}

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }

    //post construct
    @PostConstruct
    void seeWords(){
        List<Word> wordsList = wordRepository.getWordBySynonymIds("college");
        logger.info("words="+wordsList);
    }
}
