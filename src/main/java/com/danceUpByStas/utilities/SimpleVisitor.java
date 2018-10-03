package com.danceUpByStas.utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.nio.file.FileVisitResult.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.*;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.attribute.*;

public class SimpleVisitor extends SimpleFileVisitor<Path> {

    private final Path target;
    private final Path source;
    private final Logger logger = LogManager.getLogger(this.getClass());

    public SimpleVisitor(Path target, Path source) {

        this.target = target;
        this.source = source;
    }

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