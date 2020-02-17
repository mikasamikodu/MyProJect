package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test2 {
	public int[] twoSum(int[] nums, int target) {
		int length = nums.length ;
		int[] result = new int[2];
		for(int i=0; i<length; i++) {
			for(int j=i+1; j<length; j++) {
				if(nums[i]+nums[j]==target) {
					result[0] = i;
					result[1] = j;
					break;
				}
			}
		}
		return result;
	}
	
	
    public int lengthOfLongestSubstring(String s) {     
    	int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
        	char charAt = s.charAt(j);
            if (map.containsKey(charAt)) {
                i = Math.max(map.get(charAt), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(charAt, j + 1);
        }
        return ans;
    }
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        if(len1 == 0)
            return (len2%2==0)?Double.valueOf(nums2[len2/2]+nums2[len2/2-1])/2:nums2[(len2-1)/2];
        if(len2 == 0)
            return (len1%2==0)?Double.valueOf(nums1[len1/2]+nums1[len1/2-1])/2:nums1[(len1-1)/2];
        List<Integer> all = new ArrayList<Integer>();
        int i, j;
        for(i = 0, j = 0;i<len1&&j<len2;){
            if(nums1[i]<nums2[j]){
                all.add(nums1[i++]);
            }else{
                all.add(nums2[j++]);
            }
        }
        while(i<len1){
            all.add(nums1[i++]);
        }
        while(j<len2){
            all.add(nums2[j++]);
        }
        int len = all.size();
        return (len%2==0)?Double.valueOf(all.get(len/2)+all.get(len/2-1))/2:all.get((len-1)/2);
    }
    
    public double findMedianSortedArrays2(int[] A, int[] B) {
        int la = A.length;//���� A �ĳ���
        int lb = B.length;//���� B �ĳ���

        if (la > lb) {//������� A �Ƚϳ����򽻻� A��B ���飬ȷ������A������B��
            int[] temp = A;
            A = B;
            B = temp;

            int tempL = la;
            la = lb;
            lb = tempL;
        }

        int aMin = 0;     //�۰������A����Ϊ��׼ʱ��A������߽�
        int aMax = la;    //�۰������A����Ϊ��׼ʱ��A�����ұ߽�

        // halfLen �����þ����е����꣬�� A �������۰���������ƶ�ʱ��B ������ halfLen Ϊ��Ե������ƶ����Ա���ʼ�վ���
        int halfLen = (la + lb + 1) >> 1;
        //���ֲ���

        //���һ: A ����Ϊ�գ���λ���� B ����
        //�����: A ����϶�
        //  1) A ����Ԫ�ض���С����λ����B����
        //  2) A ����Ԫ�ض��ϴ���λ����B����
        //  3) A��B Ԫ�ش�С�ֲ������൱����λ��Ϊ���ָ����������벿�ֽϴ����һ�����Ұ벿�ֽ�С����һ��֮�͵�һ��
        //�����: A��B �ȳ�
        //  1) A ����Ԫ�ض���B����Ԫ��С����λ��Ϊ A ����βԪ�غ�B������Ԫ��֮�͵�һ��
        //  2) B ����Ԫ�ض���A����Ԫ��С����λ��Ϊ B ����βԪ�غ�A������Ԫ��֮�͵�һ��
        //  3) A��B Ԫ�ش�С�ֲ������൱����λ��Ϊ���ָ����������벿�ֽϴ����һ�����Ұ벿�ֽ�С����һ��֮�͵�һ��
        while (aMin <= aMax) {
            int aIndex = (aMin + aMax) >> 1;  //A�����зָ��
            int bIndex = halfLen - aIndex;  //B�����зָ��

            //���� A �ָ����������Ǹ�Ԫ�ر����� B �ָ�������ұ��Ǹ�Ԫ�ش���Ӧ�ý����� A �ָ�������ƣ����� B �ָ��������
            //���� A �ָ�������������ƣ�������߽�
            if (aIndex > aMin && A[aIndex - 1] > B[bIndex]) {
                aMax = aIndex - 1;
                //���� A �ָ�������ұ��Ǹ�Ԫ�ر����� B �ָ����������Ǹ�Ԫ�ش���Ӧ�ý����� A �ָ�������ƣ����� B �ָ��������
                //���� A �ָ�������������ƣ������ұ߽�
            } else if (aIndex < aMax && B[bIndex - 1] > A[aIndex]) {
                aMin = aIndex + 1;
                //�ó����
            } else {

                int leftPart = 0;
                //���һ,�����2�������2
                if (aIndex == 0) {//aIndex == 0˵��A����Ϊ��
                    leftPart = B[bIndex-1];
                    //�����1
                } else if (bIndex == 0) {
                    leftPart = A[la - 1];
                    //�����1,�����3,�����3
                } else {
                    leftPart = Math.max(A[aIndex - 1], B[bIndex-1]);
                }

                //Ԫ�ظ����ܺ�Ϊ����
                if ((la + lb) % 2 == 1) {
                    return leftPart;
                }
                //Ԫ�ظ����ܺ�Ϊż��
                int rightPart = 0;
                //���һ,�����1
                if (aIndex == la) {
                    rightPart = B[bIndex];
                    //�����2
                } else if (bIndex == lb) {
                    rightPart = A[0];
                    //�����2��3�������1��3
                }else {
                    rightPart = Math.min(A[aIndex], B[bIndex]);
                }
                return (leftPart + rightPart) / 2.0;
            }

        }
        return 0;
    }
	

}
