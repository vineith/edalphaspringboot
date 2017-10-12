package com.edalpha;

import com.edalpha.service.PDFReader;
import com.edalpha.service.TranscriptUS;
import org.apache.tika.exception.TikaException;
import org.hamcrest.Matchers;
import org.junit.Before;
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

    @Before


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
        String text = "Name: Rob Green +\n" + "GA 30328 GPA";
        assertThat(transcriptUS.getStudentName(text), Matchers.is("Rob Green"));
        text = "name Rob Green +\n" + "GA 30328 GPA";
        assertThat(transcriptUS.getStudentName(text), Matchers.is("Rob Green"));
        text = "NAME Rob Green +\n" + "GA 30328 GPA";
        assertThat(transcriptUS.getStudentName(text), Matchers.is("Rob Green"));
        text = "Issued to Rob Green +\n" + "GA 30328 GPA";
        assertThat(transcriptUS.getStudentName(text), Matchers.is("Rob Green"));
        text = "Record of Rob Green +\n" + "GA 30328 GPA";
        assertThat(transcriptUS.getStudentName(text), Matchers.is("Rob Green"));
        text = "Issued to: Rob Green +\n" + "GA 30328 GPA";
        assertThat(transcriptUS.getStudentName(text), Matchers.is("Rob Green"));
        text = "Record of: Rayaj A. Ahmad +\n" + "GA 30328 GPA";
        assertThat(transcriptUS.getStudentName(text), Matchers.is("Rayaj A. Ahmad"));
        text = "Issued To \n Rayaj A. Ahmad +\n" + "GA 30328 GPA";
        assertThat(transcriptUS.getStudentName(text), Matchers.is("Rayaj A. Ahmad"));






    }

    @Test
    public void readBirthDate() throws IOException, TikaException {
        File file = new File("src/test/resources/sample-us-transcript.pdf");
        InputStream inputStream = new FileInputStream(file);
        String contents = pdfReader.read(inputStream);
        System.out.println(contents);
        String text = "blah blah 21-DEC-2018";
        assertThat(transcriptUS.getBirthDate(text),Matchers.is(""));
        text = "blah birth blah 21-DEC-2018";
        assertThat(transcriptUS.getBirthDate(text),Matchers.is("21-DEC-2018"));
        text = "blah birth blah May 12, 2018";
        assertThat(transcriptUS.getBirthDate(text),Matchers.is("MAY 12, 2018"));
        text = "blah birth blah 9/16/1982";
        assertThat(transcriptUS.getBirthDate(text),Matchers.is("9/16/1982"));
        text = "blah birth blah 9/16/1983";
        assertThat(transcriptUS.getBirthDate(text),Matchers.is("9/16/1983"));
        text = "blah blah blah 9/16/1983";
        assertThat(transcriptUS.getBirthDate(text),Matchers.is(""));

    }

    @Test
    public void readMajor() throws IOException, TikaException {
        String text = "Major:Chemistry";
        assertThat(transcriptUS.getMajor(text), Matchers.is("Chemistry"));
        text = " afafaMajor : Natural Resources";
        assertThat(transcriptUS.getMajor(text), Matchers.is("Natural Resources"));
        text = "major : Natural Resources";
        assertThat(transcriptUS.getMajor(text), Matchers.is("Natural Resources"));
        text = "major : Natural & Resources";
        assertThat(transcriptUS.getMajor(text), Matchers.is("Natural & Resources"));
        text = "  major : Natural & Resources +\n"+"blah blah";
        assertThat(transcriptUS.getMajor(text), Matchers.is("Natural & Resources"));
        text="Major : Natural Resources \n" +
                " \n" +
                "SUBJ NO. COURSE TITLE CRED GRD PTS R";
        assertThat(transcriptUS.getMajor(text), Matchers.is("Natural Resources"));
        File file = new File("src/test/resources/sample-us-transcript.pdf");
        InputStream inputStream = new FileInputStream(file);
        String contents = pdfReader.read(inputStream);
        assertThat(transcriptUS.getMajor(contents), Matchers.is("Natural Resources"));
        text="Major and Department: Chemistry, Sch/Chemistry and\n" +
                "Biochemistry";
       // make it work later** assertThat(transcriptUS.getMajor(text), Matchers.is("Chemistry, Sch/Chemistry and Biochemistry"));
        System.out.println(contents);


    }

    @Test
    public void getOverallGpa() throws IOException, TikaException{
        File file = new File("src/test/resources/dummy_transcript_kt.pdf");
        InputStream inputStream = new FileInputStream(file);
        String contents = pdfReader.read(inputStream);
        //System.out.println(contents);
        //System.out.println("---->"+transcriptUS.getOverallGpa(contents));
        assertThat(transcriptUS.getOverallGpa(contents), Matchers.is("3.17"));
        String text = "Overall blah blah blah 3. jhsdhjshdbjhshdjhsjd jhjsdhjshjds djhsdjhsdjhsd \n \n \n \n " +
                "3.50";
        assertThat(transcriptUS.getOverallGpa(text), Matchers.is("3.50"));

    }

    @Test
    public void getAllGpa() throws IOException, TikaException{
        //File file = new File("src/test/resources/sample-us-transcript.pdf");
        File file = new File("src/test/resources/dummy_transcript_kt.pdf");
        InputStream inputStream = new FileInputStream(file);
        String contents = pdfReader.read(inputStream);
        System.out.println(contents);
        for (String str:transcriptUS.getSemesterGpas(contents)) {
            System.out.println("-->"+str);
        }

    }


}
