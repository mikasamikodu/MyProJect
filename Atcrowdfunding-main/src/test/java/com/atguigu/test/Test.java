package com.atguigu.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.activity.listener.NoListener;
import com.atguigu.activity.listener.YesListener;

@SuppressWarnings("unused")
public class Test {
	
	ApplicationContext app = new ClassPathXmlApplicationContext("spring/spring*.xml");
	ProcessEngine service = app.getBean(ProcessEngine.class);
	//ʹ����������ProcessEngine�Դ˴���������ݿ��
	@org.junit.Test
	public void test() {
		System.out.println(service);
	}
	
	//ʹ��RepositoryService�������̵Ĳ���
	@org.junit.Test
	public void test2() {
		RepositoryService repositoryService = service.getRepositoryService();
		Deployment deploy = repositoryService.createDeployment().addClasspathResource("MyProcess9.bpmn").deploy();
		System.out.println(deploy);//DeploymentEntity[id=301, name=null]
	}
	
	//ʹ��RepositoryService�������̶����ѯProcessDefinitionQuery��ʹ�����api��ѯ���̶��������Ϣ
	@org.junit.Test
	public void test3() {
		RepositoryService repositoryService = service.getRepositoryService();
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		//��ѯ���̲�����Ϣ���б�
//		List<ProcessDefinition> list = query.list();
//		for(ProcessDefinition processDefinition:list) {
//			System.out.println(processDefinition.getId());
//			System.out.println(processDefinition.getKey());
//			System.out.println(processDefinition.getName());
//			System.out.println(processDefinition.getVersion());
//			System.out.println("---------------------");
//		}
		
		//��ѯ���̲�����Ϣ��������
//		long count = query.count();
//		System.out.println("count="+count);
		
		//��ѯ���̲�������һ�β������Ϣ
//		ProcessDefinition result = query.latestVersion().singleResult();
//		System.out.println("result="+result);
		
		//�����̶���İ汾�ŵ������򣬲�ѯ���̲������Ϣ
		ProcessDefinitionQuery query2 = query.orderByProcessDefinitionVersion().desc();
		List<ProcessDefinition> listPage = query2.listPage(0, 2);
		for(ProcessDefinition processDefinition:listPage) {
			System.out.println(processDefinition.getId());
			System.out.println(processDefinition.getKey());
			System.out.println(processDefinition.getName());
			System.out.println(processDefinition.getVersion());
			System.out.println("---------------------");
		}
		
	} 
	
	//ʹ��RepositoryService�������̶����Ի�ȡĳ�����̵�id��
	//ʹ��RuntimeService��������id��������ʵ��ProcessInstance
	@org.junit.Test
	public void test4() {
		ProcessDefinition result = service.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
		ProcessInstance instance = service.getRuntimeService().startProcessInstanceById(result.getId());
		System.out.println(instance);//ProcessInstance[1501]
		
	}
	
	//����TaskService��Ȼ�󴴽�TaskQuery���Դ˲�ѯ���˵ĵ���������
	@org.junit.Test
	public void test5() {
		TaskService taskService = service.getTaskService();
		TaskQuery taskQuery = taskService.createTaskQuery();
		List<Task> list = taskQuery.taskAssignee("zhangsan").list();
		List<Task> list2 = taskQuery.taskAssignee("lisi").list();
		System.out.println("zhangsan");
		for (Task task : list) {
			System.out.println("id="+task.getId());
			System.out.println("name="+task.getName());
			//id=504
			//name=�鳤����
			taskService.complete(task.getId());
		}
		System.out.println("lisi");
		for (Task task : list2) {
			System.out.println("id="+task.getId());
			System.out.println("name="+task.getName());
			//id=602
			//name=��������
			taskService.complete(task.getId());
		}
	}

	//����HistoryService��Ȼ�󴴽�HistoricProcessInstanceQuery����ѯ���̶�����ʷ�ٸ���id�������ʵ��
	@org.junit.Test
	public void test6() {
		HistoryService historyService = service.getHistoryService();
		HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
		HistoricProcessInstance result = query.processDefinitionId("401").finished().singleResult();
		System.out.println(result);
	}
	
	//�������̣���������ʵ����������ȡ����
	@org.junit.Test
	public void test7() {
		TaskService taskService = service.getTaskService();
		TaskQuery taskQuery = taskService.createTaskQuery();
		List<Task> list = taskQuery.taskCandidateGroup("mana").list();
		long count = taskQuery.taskAssignee("zhangsan").count();
		System.out.println("zhangsan����1:"+count);
		for(Task task:list) {
			System.out.println("id="+task.getId()+";name="+task.getName());
			taskService.claim(task.getId(), "zhangsan");
		}
		taskQuery = taskService.createTaskQuery();
		long count1 = taskQuery.taskAssignee("zhangsan").count();
		System.out.println("zhangsan����2:"+count);
		
	}
	
	//���̱���
	@org.junit.Test
	public void test8() {
		ProcessDefinition result = service.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("tl", "zhangsan");
//		map.put("pm", "lisi");
		map.put("day", 3);
		ProcessInstance instance = service.getRuntimeService().startProcessInstanceById(result.getId(), map);
		System.out.println(instance);//ProcessInstance[2401]
		
	}
	
	//������������
	@org.junit.Test
	public void test9() {
		TaskService taskService = service.getTaskService();
		TaskQuery taskQuery = taskService.createTaskQuery();
		List<Task> list = taskQuery.taskAssignee("zhangsan").list();
		for(Task task:list) {
			taskService.complete(task.getId());
		}
		
	}
	
	//����������Ӽ�����
	@org.junit.Test
	public void test10() {
		ProcessDefinition result = service.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
		RuntimeService runtimeService = service.getRuntimeService();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("yesListener", new YesListener());
		map.put("noListener", new NoListener());
		ProcessInstance instance = runtimeService.startProcessInstanceById(result.getId(), map);
//		List<ProcessInstance> list = service.getRuntimeService().createProcessInstanceQuery().list();
//		for (ProcessInstance processInstance : list) {
//			System.out.println(processInstance.getProcessVariables().get("noListener"));
//		}
		System.out.println(instance.getProcessVariables());//ProcessInstance[401]
		
	}
	
	@org.junit.Test
	public void test11() {
		ProcessDefinition result = service.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
		TaskService taskService = service.getTaskService();
		TaskQuery taskQuery = taskService.createTaskQuery();
		List<Task> list = taskQuery.taskAssignee("zhangsan").list();
		for(Task task:list) {
			taskService.setVariable(task.getId(), "flag", "true");
			//System.out.println(taskService.getVariable(task.getId(), "flag"));
			taskService.complete(task.getId());
		}
		
	}	
	
}
