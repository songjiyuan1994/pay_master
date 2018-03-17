package com.pay.main.payment.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求处理基类，封装了响应数据的公共方法。统一响应数据格式；
 */
public class MybatisPaging {

	/**
	 * 构造分页信息
	 * 
	 * @param pageSize
	 * @param currentPageNum
	 * @return
	 */
	public PageBounds buildPageBounds(Integer pageSize, Integer currentPageNum, Boolean isContainsTotalCount) {
		if (null == pageSize) {
			pageSize = 10;
		}
		if (null == currentPageNum) {
			currentPageNum = 1;
		}
		if (null == isContainsTotalCount) {
			isContainsTotalCount = true;
		}
		PageBounds pageBounds = new PageBounds();
		pageBounds.setPage(currentPageNum);
		pageBounds.setLimit(pageSize);
		pageBounds.setContainsTotalCount(isContainsTotalCount);
		return pageBounds;
	}

	/**
	 * 构造分页数据集
	 * 
	 * @param pageSize
	 * @param currentPageNum
	 * @param ret
	 * @return
	 */
	public Map<String, Object> buildResult(Integer pageSize, Integer currentPageNum, PageList<?> ret, Integer group) {

		if (null == pageSize) {
			pageSize = 10;
		}
		if (null == currentPageNum) {
			currentPageNum = 1;
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("state", true);
		result.put("data", ret);
		result.put("rows", pageSize);
		result.put("page", currentPageNum);
		result.put("group", group);
		if (null != ret && null != ret.getPaginator()) {
			result.put("total", ret.getPaginator().getTotalCount());
		}
		return result;
	}

	/**
	 * 构造响应数据，包含state和data两个数据项
	 * 
	 * @param ret
	 * @return
	 */
	public Map<String, Object> buildResult(Object ret) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("state", true);
		result.put("data", ret);
		return result;
	}

	public Map<String, Object> buildResult(Integer pageSize, Integer currentPageNum, PageList<?> ret, String income,
			String outcome) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("state", true);
		result.put("income", income);
		result.put("outcome", outcome);
		result.put("rows", ret);
		result.put("total", ret.getPaginator().getTotalCount());
		return result;
	}

	/**
	 * 构造响应数据，包含state一个数据项
	 * 
	 * @return
	 */
	public Map<String, Object> buildResult() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("state", true);
		return result;
	}

	/**
	 * 构造响应数据，包含state和errorMsg两个数据项
	 * 
	 * @param errorMsg
	 * @return
	 */
	public Map<String, Object> buildErrorResult(String errorMsg) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("state", false);
		result.put("errorMsg", errorMsg);
		return result;
	}
}