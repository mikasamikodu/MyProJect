//
// ���ļ����� JavaTM Architecture for XML Binding (JAXB) ����ʵ�� v2.2.8-b130911.1802 ���ɵ�
// ����� <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
// ����ʱ��: 2018.08.20 ʱ�� 10:43:32 AM CST 
//


package org.example.classroom1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>classroom1Type complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="classroom1Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="grade" type="{http://www.example.org/classroom1}gradeType"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="student">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="student1" type="{http://www.example.org/classroom1}student1Type"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "classroom1Type", propOrder = {
    "grade",
    "name",
    "student"
})
public class Classroom1Type {

    protected int grade;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected Classroom1Type.Student student;

    /**
     * ��ȡgrade���Ե�ֵ��
     * 
     */
    public int getGrade() {
        return grade;
    }

    /**
     * ����grade���Ե�ֵ��
     * 
     */
    public void setGrade(int value) {
        this.grade = value;
    }

    /**
     * ��ȡname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * ����name���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * ��ȡstudent���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Classroom1Type.Student }
     *     
     */
    public Classroom1Type.Student getStudent() {
        return student;
    }

    /**
     * ����student���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Classroom1Type.Student }
     *     
     */
    public void setStudent(Classroom1Type.Student value) {
        this.student = value;
    }


    /**
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="student1" type="{http://www.example.org/classroom1}student1Type"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "student1"
    })
    public static class Student {

        @XmlElement(required = true)
        protected Student1Type student1;

        /**
         * ��ȡstudent1���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link Student1Type }
         *     
         */
        public Student1Type getStudent1() {
            return student1;
        }

        /**
         * ����student1���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link Student1Type }
         *     
         */
        public void setStudent1(Student1Type value) {
            this.student1 = value;
        }

    }

}
