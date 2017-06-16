package cn.heckman.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.heckman.framework.utils.ResponseData;
import cn.heckman.framework.utils.ShiroSessionUtil;
import cn.heckman.module.pojo.TUser;
import cn.heckman.module.pojo.TWorkFlow;
import cn.heckman.module.service.TWorkFlowService;

@Controller
@RequestMapping("/activiti")
public class ActivitiDemo {

	@Autowired
	private ProcessEngine processEngine;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private TWorkFlowService workFlowService;

	@RequestMapping("/deploy")
	@ResponseBody
	public ResponseData deploy() {
		ResponseData rd = new ResponseData();
		try {
			repositoryService// 与流程定义和部署对象相关的service
					.createDeployment()// 创建一个部署对象
					.name("helloworld入门程序")// 添加部署的名称
					.addClasspathResource("bpmn/MyProcess.bpmn")// classpath的资源中加载，一次只能加载
					.addClasspathResource("bpmn/MyProcess.png")// classpath的资源中加载，一次只能加载
					.deploy();// 完成部署

			rd.setCode("0");
		} catch (Exception ex) {
			ex.printStackTrace();
			rd.setCode("1");
		}
		return rd;
	}

	@RequestMapping("/start")
	@ResponseBody
	public ResponseData startProcessInstance() {
		ResponseData rd = new ResponseData();
		try {
			// 流程定义的key
			String processDefinitionKey = "myProcess";

			Map<String, Object> variables = new HashMap<String, Object>();
			TUser user = (TUser) ShiroSessionUtil
					.getSession(ShiroSessionUtil.USER_SESSION_NAME);
			variables.put("registerUser", user.getuId() + "");

			// 于正在执行的流程实例和执行对象相关的Service
			// 使用流程定义的key启动流程实例，key对应hellworld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
			// runtimeService.startProcessInstanceByKey(processDefinitionKey,1,variables);
			runtimeService.startProcessInstanceByKey(processDefinitionKey, "1",
					variables);
			
			

			TWorkFlow t = new TWorkFlow();
			t.setProDefinitionKey(processDefinitionKey);
			t.setStartUserId(user.getuId());
			t.setTaskIsFinish("N");
			t.setTaskStartTime(new Date());
			t.setTaskName("LeaveDate");
			workFlowService.insert(t);

			rd.setCode("0");
		} catch (Exception ex) {
			ex.printStackTrace();
			rd.setCode("1");
		}
		return rd;
	}

	@RequestMapping("/complete")
	@ResponseBody
	public ResponseData completeMyPersonTask(HttpServletRequest request) {
		ResponseData rd = new ResponseData();
		try {
			String taskId = request.getParameter("taskId");
			// 任务Id
			// String taskId = "22503";
			Task task = taskService.createTaskQuery().taskId(taskId)
					.singleResult();
			String processInstancesId = task.getProcessInstanceId();
			taskService.addComment(taskId, processInstancesId, "尝试添加批注");
			// 与正在执行的认为管理相关的Service
			taskService.complete(taskId);
			System.out.println("完成任务:任务ID:" + taskId);

			rd.setCode("0");
		} catch (Exception ex) {
			ex.printStackTrace();
			rd.setCode("1");
		}
		return rd;
	}
}
