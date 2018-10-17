package com.codefans.test.pagination;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

public class PageUtil {
	private Logger logger = Logger.getLogger(PageUtil.class);

	private PageBean pageBean;

	// ==========================style 0================
	/**
	 * 依赖spring的 JdbcTemplate获取数据，数据库格式：oracle
	 *
	 * @param pageBean
	 * @param sql
	 * @param jdbcTemplate
	 * @return
	 * @throws Exception
	 */
	public List turnPage(PageBean pageBean, String sql, JdbcTemplate jdbcTemplate) throws Exception {
		if (pageBean.getRowCount() <= 1)// 因为构造PageBean时要求rowPerPage>0,rowCount>0，但是第一次我们通常都不知道总量rowCount，所以一般设置rowCount=1，然后在分页方法中重新调整
			pageBean.setRowCount(count(jdbcTemplate, sql));

		// // 因为rowCount发生了变化，所以调整pagebean
		pageBean.setBase0(false);
		this.pageBean = adjustPageBean(pageBean); // oracle分页sql从1开始计数
		return getData(jdbcTemplate, sql, this.pageBean.getBegin(), this.pageBean.getEnd());
	}

	/**
	 * 取得记录集
	 *
	 */
	private List getData(JdbcTemplate jdbcTemplate, String sql, int begin, int end) throws Exception {
		List result = new ArrayList();
		Object[] param = new Object[] { begin, end };
		String temp = sql;
		temp = "select rownum as rn, selectedtable.* from(" + temp + ") selectedtable";
		String fullsql = "select * from (" + temp + ") where  rn>=? and rn<=?";
		logger.info(fullsql);
		logger.info("begin get resultset...");
		result = jdbcTemplate.queryForList(fullsql, param);
		logger.info("end get resultset!");
		return result;
	}

	/**
	 * 取得记录集总数,whereList包含完整的sql语句，格式1：select * from ta join tb on ta.f1=tb.f1
	 * 格式2：select * from ta join tb on ta.f1=tb.f1 group by tb.1
	 */
	private int count(JdbcTemplate jdbctemlate, String sql) throws Exception {
		int result = 0;
		String temp = sql;
		String srcSql = temp;
		temp = temp.toLowerCase();
		if (temp.indexOf("group") != -1) {
			temp = "select count(*) as cnt from (" + srcSql + " )";
		} else {
			int posfrom = temp.indexOf("from");
			temp = "select Count(*) as cnt " + srcSql.substring(posfrom);
		}
		List list = jdbctemlate.queryForList(temp);
		if (list != null && list.size() != 0) {
			Map map = (Map) list.get(0);
			result = Integer.parseInt((String) (map.get("cnt")));
		}
		return result;
	}

	// ==========================style 0================

	// ==========================style 1================
	/**
	 * 如果某一个类里有了分页获取数据的方法和计算数据总量的方法，可以调用这个方法
	 *
	 * @param targetClass
	 *            执行分页的目标类
	 * @param listMethodName
	 *            获取数据的方法
	 * @param countMethodName
	 *            计算数据总量的方法
	 * @param pageBean
	 *            分页bean
	 * @param whereObject
	 *            查询条件
	 * @return
	 * @throws Exception
	 */

	public List turnPage(Object targetClass, String listMethodName, String countMethodName, PageBean pageBean,
			Object whereObject) throws Exception {
		List result = new ArrayList();
		try {
			Object[] countParam = new Object[1];
			countParam[0] = (ArrayList) whereObject;

			Method countMethod = targetClass.getClass().getMethod(countMethodName, new Class[] { List.class });

			Method listMethod = targetClass.getClass().getMethod(listMethodName,
					new Class[] { List.class, PageBean.class });

			if (pageBean.getRowCount() <= 1)// 因为构造PageBean时要求rowPerPage>0,rowCount>0，但是第一次我们通常都不知道总量rowCount，所以一般设置rowCount=1，然后在分页方法中重新调整
				pageBean.setRowCount(((Integer) countMethod.invoke(targetClass, countParam)).intValue());

			// 因为rowCount发生了变化，所以调整pagebean
			this.pageBean = adjustPageBean(pageBean);

			Object[] ListParam = new Object[2];
			ListParam[0] = whereObject;
			ListParam[1] = pageBean;
			// ListParam[2] = new Integer(pageBean.getEnd());

			result = (List) listMethod.invoke(targetClass, ListParam);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	// ==========================style 1================
	/**
	 * 调整分页bean的值
	 */
	public PageBean adjustPageBean(PageBean pageBean) throws Exception {
		PageBean tmp = new PageBean(pageBean.getPageIndex(), pageBean.getRowPerPage(), pageBean.getRowCount(),
				pageBean.isBase0());//
		pageBean.setPageCount(tmp.getPageCount());
		pageBean.setRowCount(tmp.getRowCount());
		pageBean.setBegin(tmp.getBegin());
		pageBean.setEnd(tmp.getEnd());
		pageBean.setPageIndex(tmp.getPageIndex());
		pageBean.setRowPerPage(tmp.getRowPerPage());
		return pageBean;

	}

	/**
	 * 取得上一页、下一页等在页面显示的内容
	 *
	 * @param baseUrl
	 *            web应用的路径
	 * @return
	 */
	public String getOpContent(String baseUrl) {
		String result = "<div style=\"with:100%;text-align:right;font-size:14px;\">";

		result += "共&nbsp;" + pageBean.getRowCount() + "&nbsp;条数据&nbsp;&nbsp;";
		result += "每页&nbsp;" + pageBean.getRowPerPage() + "&nbsp;条&nbsp;&nbsp;";
		result += "第&nbsp;" + pageBean.getPageIndex() + "/" + pageBean.getPageCount() + "&nbsp;页&nbsp;&nbsp;";

		result += "<A class=\"turnpage\" HREF=\"javascript:gotoPage(1)\">首页</A>&nbsp;&nbsp;";
		result += "<A class=\"turnpage\" HREF=\"javascript:gotoPage(" + (pageBean.getPageIndex() - 1)
				+ ")\">上一页</A>&nbsp;&nbsp;";

		result += "<A class=\"turnpage\" HREF=\"javascript:gotoPage(" + (pageBean.getPageIndex() + 1)
				+ ")\">下一页</A>&nbsp;&nbsp;";
		result += "<A class=\"turnpage\" HREF=\"javascript:gotoPage(" + pageBean.getPageCount()
				+ ")\">尾页</A>&nbsp;&nbsp;";

		result += "转到第<input type=\"text\" style=\"width:30px\" name=\"jumpPage\" value=\"" + pageBean.getPageIndex()
				+ "\" size=\"5\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" onafterpaste=\"this.value=this.value.replace(/\\/g,'')\">页";
		result += "&nbsp;<input type=\"button\" name=\"Go\" value=\"Go\" onclick=\"Jumping()\">&nbsp;&nbsp;";

		// 这三个参数用来构造PageBean，rowCount在页面保存正确的值，那么就不用每次都去java方法里获取总数了
		result += "<input type='hidden' name='pageIndex' value='" + pageBean.getPageIndex() + "' />";
		result += "<input type='hidden' name='rowPerPage' value='" + pageBean.getRowPerPage() + "' />";
		result += "<input type='hidden' name='rowCount' value='" + pageBean.getRowCount() + "' />";
		result += "</div>";
		return result;
	}
}
