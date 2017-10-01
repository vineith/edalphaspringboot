package com.edalpha;

import com.edalpha.service.PDFReader;
import com.edalpha.service.TranscriptUS;
import org.apache.tika.exception.TikaException;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;
import java.io.*;

/**
 * Created by kaul on 9/29/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TranscriptReaderTest {
    @Inject
    TranscriptUS transcriptUS;
    @Inject
    PDFReader pdfReader;

    @Test
    public void readTranscripts() throws IOException, TikaException {
        String text1 = "University of Georgia +\n" + "GA 30328 GPA";
        assertThat(transcriptUS.getSchoolName(text1), Matchers.is("University of Georgia"));
        String text2 = "School of Georgia +\n" + "GA 30328 GPA";
        assertThat(transcriptUS.getSchoolName(text2), Matchers.is("School of Georgia"));
        String text3 = "The College of ABC +\n" + "GA 30328 GPA";
        assertThat(transcriptUS.getSchoolName(text3), Matchers.is("The College of ABC"));
        String text4 = "The absense of blah +\n" + "GA 30328 GPA";
        assertThat(transcriptUS.getSchoolName(text4), Matchers.is(""));
        String text5 = "The university states that this student has academic record of blah +\n" + "GA 30328 GPA";
        assertThat(transcriptUS.getSchoolName(text5), Matchers.is(""));
        File file = new File("src/test/resources/sample-us-transcript.pdf");
        InputStream inputStream = new FileInputStream(file);
        String contents = pdfReader.read(inputStream);
        assertThat(transcriptUS.getSchoolName(contents), Matchers.is("THE UNIVERSITY OF NEW MEXICO"));


    }

    @Test
    public void readName() throws IOException, TikaException {
        String text1 = "Rob Green +\n" + "GA 30328 GPA";
        assertThat(transcriptUS.getStudentName(text1), Matchers.is("Rob Green"));

    }

    @Test
    public void readBirthDate() throws IOException, TikaException {
        File file = new File("src/test/resources/sample-us-transcript.pdf");
        InputStream inputStream = new FileInputStream(file);
        String contents = pdfReader.read(inputStream);
        System.out.println(contents);
        System.out.println("----"+transcriptUS.getBirthDate(contents));
        //assertThat(transcriptUS.getBirthDate(contents), Matchers.is("THE UNIVERSITY OF NEW MEXICO"));

    }
}
