package string;

public class StringCompareEMP {

//	�Ƚ��ַ���
	public static void main(String[] args) {
		String str1 = "bsdhjfbd";
		String str2 = "sdhj";
		/*���ַ���ת��Ϊ�ַ����鲢����Ƚ���������ַ���ֱ���������߲�һ��ʱ���������ߵ�ASCll�Ĳ�ֵ
		 * ���һ���ַ�����ǰ�沿������һ���ַ�����ͬ���򷵻������ַ�������֮��
		 */
		System.out.println(str1.compareTo(str2));
		/*���ȴ���һ��CaseInsensitiveComparator���ʵ����Ȼ������ȥ�Ƚ��������ַ�����
		 * ʹ��CharAtѭ���Ƚ������ַ����е��ַ���������Ҫ�Ƚ�����ת��Ϊ��д�ַ��ٱȽϣ��ȽϺ��粻ͬ�ͽ��ַ�ת��ΪСл���ٱȽ�
		 * �������ͬ�ͷ��������ַ���ASCll�Ĳ�ֵ�����һ���ַ�����ǰ�沿������һ���ַ�����ͬ���򷵻������ַ�������֮��
		 */
		System.out.println(str2.compareToIgnoreCase(str1));
	}

}
