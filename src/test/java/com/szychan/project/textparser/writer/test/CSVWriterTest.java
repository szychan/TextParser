package com.szychan.project.textparser.writer.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import com.szychan.project.textparser.test.TestUtils;
import com.szychan.project.textparser.writer.CSVWriter;

public class CSVWriterTest {

	PrintWriter writer;
	CSVWriter csvWriter;
	File output;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Before
	public void setUp() throws Exception {
		output = temporaryFolder.newFolder("reports").toPath().resolve("output.txt").toFile();
		writer = TestUtils.getPrintWriter(output);
		csvWriter = new CSVWriter(writer, 4);
	}

	@After
	public void tearDown() throws Exception {
		writer.close();
	}

	@Test
	public void testWriteHeader() {
		csvWriter.writeHeader();
		assertEquals(", Word 1, Word 2, Word 3, Word 4", TestUtils.readFileContentToString(output));
	}

	@Test
	public void testWriteSentence() {
		String[] words1 = new String[] { "Hello", "there", "my", "friend" };
		String[] words2 = new String[] { "How", "are", "you" };
		csvWriter.writeSentence(words1);
		csvWriter.writeSentence(words2);
		assertEquals("Sentence 1, Hello, there, my, friendSentence 2, How, are, you",
				TestUtils.readFileContentToString(output));
	}

	@Test
	public void testWriteSentenceIllegalArgumentException() {
		String[] words1 = new String[] { "Hello", "there", "my", "friend" };
		String[] words2 = new String[] { "How", "are", "you", "holding", "up" };
		exception.expect(IllegalArgumentException.class);
		csvWriter.writeSentence(words1);
		csvWriter.writeSentence(words2);

	}

	@Test
	public void testWriteStopper() {
		csvWriter.writeStopper();
		assertEquals("", TestUtils.readFileContentToString(output));
	}

}
