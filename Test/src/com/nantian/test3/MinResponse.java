
package com.nantian.test3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>minResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="minResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="minResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "minResponse", propOrder = {
    "minResult"
})
public class MinResponse {

    protected int minResult;

    /**
     * ��ȡminResult���Ե�ֵ��
     * 
     */
    public int getMinResult() {
        return minResult;
    }

    /**
     * ����minResult���Ե�ֵ��
     * 
     */
    public void setMinResult(int value) {
        this.minResult = value;
    }

}
