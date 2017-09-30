package com.edalpha;

import com.edalpha.service.TranscriptUS;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

/**
 * Created by kaul on 9/29/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TranscriptReaderTest {
    @Inject
    TranscriptUS transcriptUS;

    @Test
    public void readTranscripts(){
        String text1 = "University of Georgia +\n"+"GA 30328 GPA";
        assertThat(transcriptUS.getSchoolName(text1), Matchers.is("University of Georgia"));
        String text2 = "School of Georgia +\n"+"GA 30328 GPA";
        assertThat(transcriptUS.getSchoolName(text2), Matchers.is("School of Georgia"));
        String text3 = "The College of ABC +\n"+"GA 30328 GPA";
        assertThat(transcriptUS.getSchoolName(text3), Matchers.is("The College of ABC"));



    }
}
