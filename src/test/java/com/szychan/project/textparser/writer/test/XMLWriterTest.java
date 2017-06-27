package com.szychan.project.textparser.writer.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.szychan.project.textparser.writer.XMLWriter;
import com.szychan.project.textparser.test.TestUtils;

public class XMLWriterTest {

	PrintWriter writer;
	XMLWriter xmlWriter;
	File output;

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Before
	public void setUp() throws Exception {
		output = temporaryFolder.newFolder("reports").toPath().resolve("output.txt").toFile();
		writer = TestUtils.getPrintWriter(output);
		xmlWriter = new XMLWriter(writer);

	}

	@After
	public void tearDown() throws Exception {
		writer.close();
	}

	@Test
	public void testWriteHeader() {
		xmlWriter.writeHeader();
		assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><text>",
				TestUtils.readFileContentToString(output));
	}

	@Test
	public void testWriteSentence() {
		String[] words = new String[] { "Hello", "there", "my", "friend" };
		xmlWriter.writeSentence(words);
		assertEquals("<sentence><word>Hello</word><word>there</word><word>my</word><word>friend</word></sentence>",
				TestUtils.readFileContentToString(output));
	}

	@Test
	public void testWriteStopper() {
		xmlWriter.writeStopper();
		assertEquals("</text>", TestUtils.readFileContentToString(output));
	}

}
