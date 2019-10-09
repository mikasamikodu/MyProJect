package com.atguigu.test;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricDetailQuery;
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
		Deployment deploy = repositoryService.createDeployment().addClasspathResource("MyProcess2.bpmn").deploy();
		System.out.println(deploy);//DeploymentEntity[id=401, name=null]
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
		System.out.println(instance);//ProcessInstance[501]
		
	}
	
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

	@org.junit.Test
	public void test6() {
		HistoryService historyService = service.getHistoryService();
		HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
		HistoricProcessInstance result = query.processDefinitionId("401").finished().singleResult();
		System.out.println(result);
	}
}
