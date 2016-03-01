/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.util;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class ValidationsTest {

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of isNullOrEmpty method, of class Validations.
	 */
	@Test
	public void testStringWithContentIsNotNullNorEmpty() {
		System.out.println("isNullOrEmpty");
		String text = "abcdef";
		boolean expResult = false;
		boolean result = Validations.isNullOrEmpty(text);
		assertEquals(expResult, result);
	}

	/**
	 * Test of isNullOrEmpty method, of class Validations.
	 */
	@Test
	public void testNullIsNullOrEmpty() {
		System.out.println("isNullOrEmpty");
		String text = null;
		boolean expResult = true;
		boolean result = Validations.isNullOrEmpty(text);
		assertEquals(expResult, result);
	}

	/**
	 * Test of isNullOrEmpty method, of class Validations.
	 */
	@Test
	public void testEmptyStringIsNullOrEmpty() {
		System.out.println("isNullOrEmpty");
		String text = "";
		boolean expResult = true;
		boolean result = Validations.isNullOrEmpty(text);
		assertEquals(expResult, result);
	}
}
