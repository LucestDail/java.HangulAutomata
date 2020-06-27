import java.util.ArrayList;

public class HUtil {
	static String choSoundString = "��������������������������������������";
	private static char soundTable[]={ 
		/*�ʼ� 0~18*/
		'��','��','��','��','��',
		'��','��','��','��','��',
		'��','��','��','��','��',
		'��','��','��','��',
		/*�߼� 19~39*/
		'��', '��', '��', '��','��', '��',
		'��','��', '��', '��', '��',
		'��', '��', '��', '��', '��',
		'��', '��', '��', '��', '��'
		/*���� 40~67*/
		,' ','��','��','��','��','��',
		'��','��','��','��','��','��',
		'��','��','��','��','��','��',
		'��','��','��','��','��','��',
		'��','��','��','��'
	};	
	public static char ArrTOChar(ArrayList<Character> arr) {
		char ch;
		if( arr.size() == 1 ) 
			ch= arr.get(0);
		else if( arr.size() == 2 ) 
			ch= HUtil.UniTOChar( HUtil.getJaState( arr.get(0) ) , HUtil.getMoState( arr.get(1) ) );
		else if( arr.size() == 3 ) 
			ch= HUtil.UniTOChar( HUtil.getJaState( arr.get(0) ) ,
				HUtil.getMoState( arr.get(1) ) , HUtil.getJongState( arr.get(2) ) );
		else 
			ch=HUtil.UniTOChar( HUtil.getJaState( arr.get(0) ) ,
				HUtil.getMoState( arr.get(1) ) , HUtil.getJongState( arr.get(2) ) );
		return ch;
	}
	private static char UniTOChar(int ja, int mo){
		return UniTOChar(ja, mo, 0);
	}
	
	private static char UniTOChar(int ja, int mo, int jong){
		return (char)(ja*21*28+mo*28+jong+44032);
	}
	private static int getJaState(char ja) {
//		for (int m = 0 ; m < 19  ; m++) 
//			if (ja == soundTable[m])
//				return m;
//		return -1;
		int index = choSoundString.indexOf(ja);
		return index;
	}

	private static int getMoState(char mo) {
		for (int m = 19 ; m < 40  ; m++) 
			if (mo == soundTable[m])
				return m-19;
		return -1;
	}
	
	private static int getJongState(char jo){
		for(int j = 40 ; j < 68 ; j++)
			if(jo == soundTable[j])
				return j-40;
		return -1;
	}
}