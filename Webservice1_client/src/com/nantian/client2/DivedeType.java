
package com.nantian.client2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>divedeType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="divedeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="num1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="num2" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "divedeType", propOrder = {
    "num1",
    "num2"
})
public class DivedeType {

    protected int num1;
    protected int num2;

    /**
     * ��ȡnum1���Ե�ֵ��
     * 
     */
    public int getNum1() {
        return num1;
    }

    /**
     * ����num1���Ե�ֵ��
     * 
     */
    public void setNum1(int value) {
        this.num1 = value;
    }

    /**
     * ��ȡnum2���Ե�ֵ��
     * 
     */
    public int getNum2() {
        return num2;
    }

    /**
     * ����num2���Ե�ֵ��
     * 
     */
    public void setNum2(int value) {
        this.num2 = value;
    }

}
