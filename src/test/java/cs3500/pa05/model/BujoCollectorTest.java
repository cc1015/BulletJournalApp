package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for BujoCollector class.
 */
class BujoCollectorTest {
  BujoCollector collector;
  ArrayList<String> files;
  ArrayList<String> expectedFiles;

  /**
   * initialize data
   */
  @BeforeEach
  public void initData() {
    this.files = new ArrayList<>();
    this.expectedFiles = new ArrayList<>();

    this.collector = new BujoCollector(files);
  }

  /**
   * Check that the constructor initializes properly
   */
  @Test
  public void testConstruct() {
    assertNotNull(this.collector);
  }

  /**
   * Check that the correct .md files are collected by the visitor
   */
  @Test
  public void testCollectFiles() {
    try {
      Files.walkFileTree(Path.of("bujo"), this.collector);
    } catch (IOException e) {
      fail();
    }

    this.expectedFiles.add("bujo.bujo");
    this.expectedFiles.add("bujo1.bujo");
    this.expectedFiles.add("journal.bujo");
    this.expectedFiles.add("newJournal.bujo");

    assertTrue(expectedFiles.containsAll(files));
  }

  /**
   * Checks that handler method for visitFileFailed handles the IOException given and throws a
   * RuntimeException; tests the behavior of visitFileFailed as visitFileFailed only calls this
   * handler method
   */
  @Test
  public void testFileFail() {
    assertThrows(RuntimeException.class, () -> this.collector.failHandler());
  }
}