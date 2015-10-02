package gov.samhsa.pls.web.util;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class URLArgumentTest {

	private URLArgument[] cut;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		cut = new URLArgument[] { URLArgument.CITY, URLArgument.FIRST_NAME,
				URLArgument.GENDER, URLArgument.LAST_NAME,
				URLArgument.POSTAL_CODE, URLArgument.SPECIALTY,
				URLArgument.TELEPHONE, URLArgument.US_STATE };
	}

	@Test
	public void testArgumentName() {
		assertEquals(URLArgument.URLHelper.CITY, cut[0].argumentName());
		assertEquals(URLArgument.URLHelper.FIRST_NAME, cut[1].argumentName());
		assertEquals(URLArgument.URLHelper.GENDER, cut[2].argumentName());
		assertEquals(URLArgument.URLHelper.LAST_NAME, cut[3].argumentName());
		assertEquals(URLArgument.URLHelper.POSTAL_CODE, cut[4].argumentName());
		assertEquals(URLArgument.URLHelper.SPECIALTY, cut[5].argumentName());
		assertEquals(URLArgument.URLHelper.TELEPHONE, cut[6].argumentName());
		assertEquals(URLArgument.URLHelper.US_STATE, cut[7].argumentName());
	}

	@Test
	public void testGetURLArgumentInstance() {
		Assert.assertEquals(URLArgument.URLHelper.CITY,
				URLArgument.getURLArgumentInstance(URLArgument.URLHelper.CITY)
						.argumentName());
		Assert.assertEquals(URLArgument.URLHelper.FIRST_NAME,
				URLArgument.getURLArgumentInstance(URLArgument.URLHelper.FIRST_NAME)
						.argumentName());
		Assert.assertEquals(URLArgument.URLHelper.GENDER,
				URLArgument.getURLArgumentInstance(URLArgument.URLHelper.GENDER)
						.argumentName());
		Assert.assertEquals(URLArgument.URLHelper.LAST_NAME,
				URLArgument.getURLArgumentInstance(URLArgument.URLHelper.LAST_NAME)
						.argumentName());
		Assert.assertEquals(URLArgument.URLHelper.POSTAL_CODE,
				URLArgument.getURLArgumentInstance(URLArgument.URLHelper.POSTAL_CODE)
						.argumentName());
		Assert.assertEquals(URLArgument.URLHelper.SPECIALTY,
				URLArgument.getURLArgumentInstance(URLArgument.URLHelper.SPECIALTY)
						.argumentName());
		Assert.assertEquals(URLArgument.URLHelper.TELEPHONE,
				URLArgument.getURLArgumentInstance(URLArgument.URLHelper.TELEPHONE)
						.argumentName());
		Assert.assertEquals(URLArgument.URLHelper.US_STATE,
				URLArgument.getURLArgumentInstance(URLArgument.URLHelper.US_STATE)
						.argumentName());
	}

	@Test
	public void testGetMethodType() {
		Assert.assertEquals(
				URLArgument.URLHelper.CITY_STATE_METHOD_TYPE,
				URLArgument
						.getMethodType("http://context/providers/gender/m/usstate/pa/lastname/smith"));
	}

	@Test
	public void testCreateArgumentArray() {
		String[] cityStateArray = URLArgument
				.createArgumentArray(URLArgument.URLHelper.CITY_STATE_METHOD_TYPE);
		assertEquals(14, cityStateArray.length);

		String[] zipArray = URLArgument
				.createArgumentArray(URLArgument.URLHelper.POSTAL_CODE_METHOD_TYPE);
		assertEquals(12, zipArray.length);
	}

	@Test
	public void testFillArgument() {
		String[] csArray = URLArgument
				.createArgumentArray(URLArgument.URLHelper.CITY_STATE_METHOD_TYPE);
		cut[0].fillArgument(csArray, URLArgument.URLHelper.CITY_STATE_METHOD_TYPE, "gotham");// 2
		cut[1].fillArgument(csArray, URLArgument.URLHelper.CITY_STATE_METHOD_TYPE, "john");// 6;
		cut[2].fillArgument(csArray, URLArgument.URLHelper.CITY_STATE_METHOD_TYPE, "m");// 0
		cut[3].fillArgument(csArray, URLArgument.URLHelper.CITY_STATE_METHOD_TYPE, "smith");// 5;
		cut[5].fillArgument(csArray, URLArgument.URLHelper.CITY_STATE_METHOD_TYPE,
				"general");// 3
		cut[6].fillArgument(csArray, URLArgument.URLHelper.CITY_STATE_METHOD_TYPE,
				"4105555555");// 4
		cut[7].fillArgument(csArray, URLArgument.URLHelper.CITY_STATE_METHOD_TYPE, "ny");// 1

		assertEquals("m", csArray[0]);
		assertEquals("ny", csArray[1]);
		assertEquals("gotham", csArray[2]);
		assertEquals("general", csArray[3]);
		assertEquals("4105555555", csArray[4]);
		assertEquals("smith", csArray[5]);
		assertEquals("john", csArray[6]);

		String[] zipArray = URLArgument
				.createArgumentArray(URLArgument.URLHelper.POSTAL_CODE_METHOD_TYPE);
		cut[4].fillArgument(zipArray, URLArgument.URLHelper.POSTAL_CODE_METHOD_TYPE,
				"123450987");// 2
		cut[1].fillArgument(zipArray, URLArgument.URLHelper.POSTAL_CODE_METHOD_TYPE, "john");// 6;
		cut[2].fillArgument(zipArray, URLArgument.URLHelper.POSTAL_CODE_METHOD_TYPE, "m");// 0
		cut[3].fillArgument(zipArray, URLArgument.URLHelper.POSTAL_CODE_METHOD_TYPE,
				"smith");// 5;
		cut[5].fillArgument(zipArray, URLArgument.URLHelper.POSTAL_CODE_METHOD_TYPE,
				"general");// 3
		cut[6].fillArgument(zipArray, URLArgument.URLHelper.POSTAL_CODE_METHOD_TYPE,
				"4105555555");// 4

		assertEquals("123450987", zipArray[1]);
		assertEquals("general", zipArray[2]);
		assertEquals("4105555555", zipArray[3]);
		assertEquals("smith", zipArray[4]);
		assertEquals("john", zipArray[5]);
		assertEquals("m", zipArray[0]);
	}

}
