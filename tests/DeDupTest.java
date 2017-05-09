import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class DeDupTest {
	
	private DeDup deDup;
	private DeDup deDup1;
	private DeDup deDup2;
	private DeDup deDupNull;
	private DeDup deDupEmpty;
	int[] testData = {1,2,34,34,25,1,45,3,26,85,4,34,86,25,43,2,1,10000,11,16,19,1,18,4,9,3, 13,5,11,4,7,19,16,5,9,12,3,20,7,15,17,10,6,1,8,18,4,14,13,2,11};
	int[] expectedResult ={1,1,1,1,1,2,2,2,3,3,3,4,4,4,4,5,5,6,7,7,8,9,9,10,11,11,11,12,13,13,14,15,16,16,17,18,18,19,19,20,25,25,26,34,34,34,43,45,85,86,10000};
	int[] testData1={1,1,2,2,3,4,4,4,5,5,8,8,8,8,9,9};
	int[] expectedResult1={1,2,3,4,5,8,9};
	int[] testData2={1,123,2,45,3,45,4,45,123,45,8,8,9,1};
	int[] expectedResult2={1,123,2,45,3,4,8,9};
	
	
	@Before
	public void setup(){
		deDup = new DeDup(testData);
		deDup1 = new DeDup(testData1);
		deDup2 = new DeDup(testData2);
		deDupNull=new DeDup(null);
		deDupEmpty=new DeDup(new int[0]);
	}
	
	@Test
    public void testQuickSort() {
        deDup.quicksort(0,testData.length-1);
        Assert.assertArrayEquals(expectedResult, testData);
    }

	@Test
	public void testRemoveDupsUsingSet(){
		int[] actualResult= deDup2.removeDupsUsingSet();
		Assert.assertArrayEquals(expectedResult2, actualResult);
	}
	
	@Test
	public void testRemoveDupsUsingSetForNull(){
		int[] actualResult= deDupNull.removeDupsUsingSet();
		Assert.assertArrayEquals(new int[0], actualResult);
	}
	
	@Test
	public void testRemoveDupsUsingSetForEmpty(){
		int[] actualResult= deDupEmpty.removeDupsUsingSet();
		Assert.assertArrayEquals(new int[0], actualResult);
	}
	
	//Further tests to test all units
	
	@After
	public void tearDown(){
		deDup = null;
		deDup1 = null;
		deDup2 = null;
		deDupNull=null;
		deDupEmpty=null;
	}

}


