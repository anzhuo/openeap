package com.openeap.common.utils.excel.fieldtype;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;
import com.openeap.common.utils.Collections3;
import com.openeap.common.utils.SpringContextHolder;
import com.openeap.modules.sys.entity.Role;
import com.openeap.modules.sys.service.SystemService;

/**
 * 字段类型转换
 * @author lcw
 * @version 2013-5-29
 */
public class RoleListType {

	private static SystemService systemService = SpringContextHolder.getBean(SystemService.class);
	
	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		List<Role> roleList = Lists.newArrayList();
		List<Role> allRoleList = systemService.findAllRole();
		for (String s : StringUtils.split(val, ",")){
			for (Role e : allRoleList){
				if (e.getName().equals(s)){
					roleList.add(e);
				}
			}
		}
		return roleList.size()>0?roleList:null;
	}

	/**
	 * 设置对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null){
			@SuppressWarnings("unchecked")
			List<Role> roleList = (List<Role>)val;
			return Collections3.extractToString(roleList, "name", ", ");
		}
		return "";
	}
	
}
