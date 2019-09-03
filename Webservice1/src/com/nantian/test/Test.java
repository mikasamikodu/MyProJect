package com.nantian.test;

import java.io.*;
import java.net.*;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.*;
import javax.xml.ws.soap.SOAPFaultException;
import javax.xml.xpath.*;

import org.w3c.dom.*;
import org.w3c.dom.Node;

import com.nantian.soap2.User;

public class Test {

	private String wsdlURL = "http://erptest2.sdic.com.cn:8080/GtjtIntfService/service/WsExampleService";
//	private String wsdlURL = "http://localhost:9999/server";
//	targetNamespace="http://service.interf.gtjt.cux/
	private String ns = "http://service.interf.gtjt.cux/";
//	private String ns = "http://soap2.nantian.com/";
	
	@org.junit.Test
	public void test01() {
		try {//�ȴ�����Ϣ����
			MessageFactory mess = MessageFactory.newInstance();
			//�ٴ�����Ϣ
			SOAPMessage messager = mess.createMessage();
			//�õ�soap����
			SOAPPart part = messager.getSOAPPart();
			//�õ�soap envelope
			SOAPEnvelope envelope = part.getEnvelope();
			//�õ�soap����
			SOAPBody body = envelope.getBody();
			//����qname������Ӧ�ڵ�
			QName name = new QName("http://java.nantian.edu.cn/webservice", "add","ns");
			SOAPBodyElement element = body.addBodyElement(name);
			element.addChildElement("a").setValue("22");
			element.addChildElement("b").setValue("33");
			//��ӡ��Ϣ
			messager.writeTo(System.out);
			
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@org.junit.Test
	public void test02() {
		try {
			URL url = new URL(wsdlURL);
			QName qName = new QName(ns, "MyServiceImplService");
			Service service = Service.create(url, qName);
			
			Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns,"MyServiceImplPort"), SOAPMessage.class, Service.Mode.MESSAGE);
			
			SOAPMessage msg = MessageFactory.newInstance().createMessage();
			SOAPPart part = msg.getSOAPPart();
			SOAPEnvelope envelope = part.getEnvelope();

			SOAPBody body = envelope.getBody();
			QName qname = new QName(ns, "login","ns");
			SOAPBodyElement element = body.addBodyElement(qname);
//			element.addChildElement("username").setValue("����");
			element.addChildElement("username").setValue("zhangsan");
			element.addChildElement("password").setValue("1234");
			msg.writeTo(System.out);
			System.out.println();
			
			SOAPMessage invoke = dispatch.invoke(msg);
			invoke.writeTo(System.out);
			System.out.println();
//			Document document = invoke.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
//			String result = document.getElementsByTagName("user").item(0).getTextContent();
//			System.out.println(result);
		} catch(SOAPFaultException e) {
			System.out.println(e.getMessage());
		} catch(SOAPException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@org.junit.Test
	public void test03() {
		try {
			//��������
			URL url = new URL(wsdlURL);
			QName qName = new QName(ns, "MyServiceImplService");
			Service service = Service.create(url, qName);
			//����dispatch��ͨ��Դ���ݵķ�ʽ���д���
			Dispatch<Source> dispatch = service.createDispatch(new QName(ns,"MyServiceImplPort"), Source.class, Service.Mode.PAYLOAD);
			//�����û����󴴽�xml
			User user = new User("3","tom","jack","1234");
			JAXBContext jax = JAXBContext.newInstance(User.class);
			Marshaller masl = jax.createMarshaller();
			masl.setProperty(Marshaller.JAXB_FRAGMENT, true);
			StringWriter writer = new StringWriter();
			masl.marshal(user, writer);
			
			//��װ��Ӧ��part,addUser
			String payload = "<ns:addUser xmlns:ns=\""+ns+"\">"+writer.toString()+"</ns:addUser>";
			System.out.println(payload);
			StreamSource rs = new StreamSource(new StringReader(payload));
			
			//��dispatch����payload
			Source responce = dispatch.invoke(rs);
			
			//��Sourceת��ΪDOM���в�����ʹ��Transformerת��
			Transformer trans = TransformerFactory.newInstance().newTransformer();
			DOMResult result = new DOMResult();
			trans.transform(responce, result);
			
			//ͨ��XPath������Ӧ��Ϣ
			XPath path = XPathFactory.newInstance().newXPath();
			NodeList list = (NodeList)path.evaluate("//user", result.getNode(), XPathConstants.NODESET);
			System.out.println(list.item(0).getNodeName());;
			User  user1 = (User)jax.createUnmarshaller().unmarshal(list.item(0));
			System.out.println(user1.getNickname());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@org.junit.Test
	public void test04() {
		try {
			//��������service
			URL url = new URL(wsdlURL);
			QName qname = new QName(ns,"MyServiceImplService");
			Service service = Service.create(url,qname);
			//����dispatchͨ��sourceԴ���ݵķ�ʽ��������
			Dispatch<Source> dispatch = service.createDispatch(new QName(ns,"MyServiceImplPort"), Source.class, Service.Mode.PAYLOAD);
			//�����û����󴴽�xml
			User user = new User("4","rose","rose1","1234");
			//ͨ��JAXBContext�������
			JAXBContext jaxb = JAXBContext.newInstance(User.class);
			Marshaller marshaller = jaxb.createMarshaller();
			StringWriter writer = new StringWriter();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			//��������б���
			marshaller.marshal(user, writer);
			//����xml
			String payload = "<ns:addUser xmlns:ns=\""+ns+"\">"+writer.toString()+"</ns:addUser>";
			System.out.println(payload);
			
			StreamSource source = new StreamSource(new StringReader(payload));
			//���������Ĳ�������Ӧ����
			Source responce = dispatch.invoke(source);
			//����Ӧ����ͨ��Transformerת����document�ṹ
			DOMResult result = new DOMResult();
			Transformer trans = TransformerFactory.newInstance().newTransformer();
			trans.transform(responce, result);
			//ͨ��XPath������Ӧ����
			XPath path = XPathFactory.newInstance().newXPath();
			NodeList list = (NodeList)path.evaluate("//user",result.getNode(),XPathConstants.NODESET);
			System.out.println(list.item(0).getNodeName());
			//ͨ��JAXBContext����Ӧ�����еĶ�����з����룬ת���ɶ���
			User user1 = (User)jaxb.createUnmarshaller().unmarshal(list.item(0));
			System.out.println(user1.getUsername());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch(JAXBException e) {
			e.printStackTrace();
		} catch(TransformerConfigurationException e) {
			e.printStackTrace();
		} catch(TransformerException e) {
			e.printStackTrace();
		} catch(XPathExpressionException e) {
			e.printStackTrace();
		}
	}
	
	@org.junit.Test
	public void test05() {
		try {
			//����Service
			URL url = new URL(wsdlURL);
			QName qname = new QName(ns,"MyServiceImplService");
			Service service = Service.create(url,qname);
			//����Dispatch��ͨ��SOAPMessage������Ϣ
			Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns,"MyServiceImplPort"),SOAPMessage.class,Service.Mode.MESSAGE);
			//ͨ��QName����SOAPMessage��Body
			SOAPMessage message = MessageFactory.newInstance().createMessage();
			SOAPPart part = message.getSOAPPart();
			SOAPEnvelope envelope = part.getEnvelope();
			
			SOAPHeader header = envelope.getHeader();
			if(header == null) header = envelope.addHeader();
			header.addHeaderElement(new QName(ns,"authInfo","ns")).setValue("rise");;
			
			SOAPBody body = envelope.getBody();
			body.addBodyElement(new QName(ns,"list","ns"));
			message.writeTo(System.out);
			System.out.println();
			//ͨ��dispatch���������ģ���ȡ��Ӧ����
			SOAPMessage response = dispatch.invoke(message);
			response.writeTo(System.out);
			System.out.println();
			//����Ӧ����ת��ΪDocument�ṹ
			Document dom = response.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
			//���ڵõ�����list����ֱ�ӻ�ȡ���е���ؽڵ�
			NodeList list = (NodeList)dom.getElementsByTagName("user");
			//ͨ��JAXBContext���з�����
			JAXBContext jaxb = JAXBContext.newInstance(User.class);
			for(int a=0;a<list.getLength();a++) {
				Node node = list.item(a);
				//ͨ���������ȡ����
				User user = (User)jaxb.createUnmarshaller().unmarshal(node);
				System.out.println(user.getUsername());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@org.junit.Test
	public void test06() {
		try {
			//GtjtserviceService
			URL url = new URL(wsdlURL);
			QName qName = new QName(ns, "GtjtserviceService");
			Service service = Service.create(url, qName);

			Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns,"GtjtserviceServiceSoapBinding"), SOAPMessage.class, Service.Mode.MESSAGE);
			
			SOAPMessage msg = MessageFactory.newInstance().createMessage();
			SOAPPart part = msg.getSOAPPart();
			SOAPEnvelope envelope = part.getEnvelope();

			SOAPBody body = envelope.getBody();
			QName qname = new QName(ns, "importFa","tns");
			SOAPBodyElement element = body.addBodyElement(qname);
//			element.addChildElement("username").setValue("����");
			element.addChildElement("username").setValue("zhangsan");
			element.addChildElement("password").setValue("1234");
			msg.writeTo(System.out);
			System.out.println();
			
			SOAPMessage invoke = dispatch.invoke(msg);
			invoke.writeTo(System.out);
			System.out.println();
//			Document document = invoke.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
//			String result = document.getElementsByTagName("user").item(0).getTextContent();
//			System.out.println(result);
		} catch(SOAPFaultException e) {
			System.out.println(e.getMessage());
		} catch(SOAPException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
