package com.danceUpByStas.utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.nio.file.FileVisitResult.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.*;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.attribute.*;

/**
 * This is the SimpleVisitor class designed to visit directories and files.
 * @author srevin
 */
public class SimpleVisitor extends SimpleFileVisitor<Path> {

    private final Path target;
    private final Path source;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * The parameterized constructor.
     * @param target The target path.
     * @param source The source path.
     */
    public SimpleVisitor(Path target, Path source) {

        this.target = target;
        this.source = source;
    }

    /**
     * This method will run a task when visiting the given path.
     * @param file The given path to the file.
     * @param attr The file attributes.
     * @return CONTINUE The visit result. CONTINUE means that the file walking should continue.
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {

        try {

            Files.copy(source, target, REPLACE_EXISTING);
        } catch (IOException inputOutputException) {

            logger.debug("IO Exception: {}", inputOutputException);
        }

        return CONTINUE;
    }
}