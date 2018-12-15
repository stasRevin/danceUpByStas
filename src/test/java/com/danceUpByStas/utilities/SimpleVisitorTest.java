package com.danceUpByStas.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Simple visitor test.
 * @author srevin
 */
class SimpleVisitorTest {

    /**
     * The Logger.
     */
    Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
    }

    /**
     * Visit file success.
     */
    @Test
    void visitFileSuccess() {

        String fileName = "fileOne.txt";
        Path source = Paths.get("/Users/stanislavrevin/Desktop/MATC/EnterpriseJava/indieProject/testPath/source"
                + File.separator + fileName);
        Path target = Paths.get("/Users/stanislavrevin/Desktop/MATC/EnterpriseJava/indieProject/testPath/target"
                + File.separator + fileName);
        File fileOne = new File(source.toString());
        File fileOnePrime = new File(target.toString());

        try {

            fileOne.createNewFile();
            SimpleVisitor simpleVisitor = new SimpleVisitor(target, source);
            Files.walkFileTree(source, simpleVisitor);

        } catch (IOException inputOutputException) {

            logger.debug("Input/Output Exception: {}",  inputOutputException);

        }

        boolean fileExists = fileOnePrime.exists();

        assertEquals(true, fileExists);
        fileOnePrime.delete();
    }
}