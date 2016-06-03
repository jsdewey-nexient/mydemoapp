package com.nexient.controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Joel Dewey
 *         Date: 6/2/2016
 */
public class IndexControllerTest {
	private IndexController indexController;

	@Before
	public void setUp() {
		this.indexController = new IndexController();
	}

	@Test
	public void testGetIndexPage() throws Exception {
		assertEquals("simplepage", this.indexController.getIndexPage());
	}

	@Test
	public void testGetIndexPageAlwaysFail() {
		assertEquals(
				"This will never pass.",
				"otherpage",
				this.indexController.getIndexPage()
		);
	}

}