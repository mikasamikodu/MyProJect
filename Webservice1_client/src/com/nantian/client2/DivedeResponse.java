
package com.nantian.client2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>divedeResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="divedeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="divedeResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "divedeResponse", propOrder = {
    "divedeResult"
})
public class DivedeResponse {

    protected int divedeResult;

    /**
     * ��ȡdivedeResult���Ե�ֵ��
     * 
     */
    public int getDivedeResult() {
        return divedeResult;
    }

    /**
     * ����divedeResult���Ե�ֵ��
     * 
     */
    public void setDivedeResult(int value) {
        this.divedeResult = value;
    }

}
