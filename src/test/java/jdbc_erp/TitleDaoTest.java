package jdbc_erp;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_erp.dao.TitleDao;
import jdbc_erp.dao.TitleDaoImpl;
import jdbc_erp.dto.Title;
import jdbc_erp.jdbc.LogUtil;




@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoTest {
	static TitleDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("Start TitleDaoTest");
		dao = new TitleDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("End TitleDaoTest");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		System.out.println();
	}

	@Test
	public void test01SelectTitleByAll() {
		List<Title> lists = dao.selectTitledaoByAll();
		LogUtil.prnLog(lists.toString());
		Assert.assertNotNull(lists);
	}

	@Test
	public void test02InsertTitle() {
		try {
			
			Title newtitle = new Title("A003","a");
			int rowAffected = dao.insertTitle(newtitle);
			LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
			Assert.assertEquals(1, rowAffected);
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				LogUtil.prnLog("해당 부서 존재");
			} else {
				LogUtil.prnLog(e);
			}
		}
	}

	
}
