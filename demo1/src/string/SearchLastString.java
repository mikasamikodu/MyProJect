package string;

public class SearchLastString {

	public static void main(String[] args) {
		String str1 = "dsbkjdsnk";
		String str2 = "bkj";
		int index = str1.lastIndexOf(str2);
		if(index != -1) 
			System.out.println(str2 + "��" + str1 + "�������ֵ�λ���ǵ�" + index + "���ַ�֮��");
		else
			System.out.println(str2 + "û����" + str1 + "�г���");
	}

}
