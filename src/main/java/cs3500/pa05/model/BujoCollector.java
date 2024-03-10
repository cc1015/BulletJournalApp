package cs3500.pa05.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * Represents a file visitor that walks the file system and creates a MdFile for every .md file
 * it encounters and adds the MdFile to a list (to keep track of metadata). Also adds the
 * file itself to another list to store the file itself.
 */
public class BujoCollector implements FileVisitor<Path> {
  private ArrayList<String> files;

  /**
   * Constructor for MarkdownCollector that sets mdFiles to the given list of MdFiles and sets
   * files to the given list of files.
   *
   * @param files the files to be parsed through
   */
  public BujoCollector(ArrayList<String> files) {
    this.files = files;
  }

  /**
   * Before visiting a file.
   *
   * @param dir   a reference to the directory
   * @param attrs the directory's basic attributes
   *
   * @return the visit result
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
    return FileVisitResult.CONTINUE;
  }

  /**
   * When visiting a file, check if is a .md file. If so, create a new MdFile with the metadata
   * and add it to mdFiles list.
   *
   * @param file  a reference to the file
   * @param attrs the file's basic attributes
   *
   * @return the visit result
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    File f = new File(file.toString());
    String name = f.getName();
    if (name.endsWith(".bujo")) {
      this.files.add(name);
    }
    return FileVisitResult.CONTINUE;
  }

  /**
   * When a file cannot be visited.
   *
   * @param file a reference to the file
   * @param exc  the I/O exception that prevented the file from being visited
   *
   * @return the visit result
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    failHandler();
    return FileVisitResult.CONTINUE;
  }

  /**
   * handler for visitFileFailed, throws a RuntimeException noting that the file visit has failed
   *
   */
  public void failHandler() {
    throw new RuntimeException("file cannot be visited");
  }

  /**
   * After a file is visited.
   *
   * @param dir a reference to the directory
   * @param exc {@code null} if the iteration of the directory completes without
   *            an error; otherwise the I/O exception that caused the iteration
   *            of the directory to complete prematurely
   *
   * @return the visit result
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
    return FileVisitResult.CONTINUE;
  }
}