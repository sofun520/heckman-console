package cn.heckman.framework.utils;

public class ActivitiConstant {

	public static String LEAVE_PROCESS = "leave_process";

	public static String getTaskName(String taskKey) {
		if (LEAVE_PROCESS.equals(taskKey)) {
			return "员工请假流程";
		}
		return null;
	}

}
