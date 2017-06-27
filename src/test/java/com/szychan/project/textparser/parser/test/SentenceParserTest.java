package com.szychan.project.textparser.parser.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.szychan.project.textparser.parser.SentenceParser;
import com.szychan.project.textparser.test.TestUtils;

public class SentenceParserTest {
	PrintWriter writer;
	SentenceParser sentenceParser;
	File output;

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Before
	public void setUp() throws Exception {
		output = temporaryFolder.newFolder("reports").toPath().resolve("output.txt").toFile();
		writer = TestUtils.getPrintWriter(output);
		sentenceParser = new SentenceParser(writer);
	}

	@After
	public void tearDown() throws Exception {
		writer.close();
	}

	@Test
	public void testParse1() {
		sentenceParser.parse("What	he  shouted was shocking.");
		assertEquals("he shocking shouted was What ", TestUtils.readFileContentToString(output));
	}

	@Test
	public void testGetMaxWordsWithNoParsingDone() {
		sentenceParser.getMaxWords();
		assertEquals(0, sentenceParser.getMaxWords());
	}

	@Test
	public void testGetMaxWords() {
		sentenceParser.parse("hello there mister. how are you today.");
		sentenceParser.parse("I am fine, thank you very much. blabla");
		sentenceParser.parse("ups.");
		sentenceParser.getMaxWords();
		assertEquals(7, sentenceParser.getMaxWords());
	}

}
