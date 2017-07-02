package com.edalpha.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import java.io.*;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import javax.inject.Named;


/**
 * Created by kaul on 5/3/17.
 */
@Named
public class PDFReader {

        private Logger logger = LoggerFactory.getLogger(PDFReader.class);

        public String  read(InputStream inputStream) throws IOException,TikaException {

            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            ParseContext pcontext = new ParseContext();

            //parsing the document using PDF parser
            PDFParser pdfparser = new PDFParser();
            try {
                pdfparser.parse(inputStream, handler, metadata,pcontext);
            } catch (SAXException e) {
                e.printStackTrace();
            }


            //getting metadata of the document
            logger.debug("Metadata of the PDF:");
            String[] metadataNames = metadata.names();

            for(String name : metadataNames) {
               logger.debug(name+ " : " + metadata.get(name));
            }
            logger.debug(handler.toString());
            return handler.toString();

    }
}
