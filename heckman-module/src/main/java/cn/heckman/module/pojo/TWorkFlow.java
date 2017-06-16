package cn.heckman.module.pojo;

import java.util.Date;

public class TWorkFlow {
	/**
     * 
     */
	private Integer id;

	/**
     * 
     */
	private Integer startUserId;

	/**
     * 
     */
	private String proDefinitionKey;

	/**
     * 
     */
	private String taskProId;

	/**
     * 
     */
	private String taskName;

	/**
     * 
     */
	private Date taskStartTime;

	/**
     * 
     */
	private Date taskFinishTime;

	/**
	 * 是否完成: 完成 Y ; 未完成 N
	 */
	private String taskIsFinish;

	private String taskId;

	private String actName;

	private String assignee;

	private Date startTime;

	private Date endTime;

	private String message;

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStartUserId() {
		return startUserId;
	}

	public void setStartUserId(Integer startUserId) {
		this.startUserId = startUserId;
	}

	public String getProDefinitionKey() {
		return proDefinitionKey;
	}

	public void setProDefinitionKey(String proDefinitionKey) {
		this.proDefinitionKey = proDefinitionKey == null ? null
				: proDefinitionKey.trim();
	}

	public String getTaskProId() {
		return taskProId;
	}

	public void setTaskProId(String taskProId) {
		this.taskProId = taskProId == null ? null : taskProId.trim();
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName == null ? null : taskName.trim();
	}

	public Date getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(Date taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public Date getTaskFinishTime() {
		return taskFinishTime;
	}

	public void setTaskFinishTime(Date taskFinishTime) {
		this.taskFinishTime = taskFinishTime;
	}

	public String getTaskIsFinish() {
		return taskIsFinish;
	}

	public void setTaskIsFinish(String taskIsFinish) {
		this.taskIsFinish = taskIsFinish == null ? null : taskIsFinish.trim();
	}
}