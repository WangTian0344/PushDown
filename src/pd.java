import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class pd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char cin[]=Stringinput();
		//��ʼ��״̬ת�Ƽ���
		ArrayList<State_trans> State_trans_list=State_trans_init();
		//ջ
		Stack<String> stack=new Stack<String>();
		
		//State_trans_print(State_trans_list);
		if(Stringaccept(cin,State_trans_list,stack))
			System.out.println("�ַ����������Զ�������!");
		else
			System.out.println("�ַ������������Զ�������!");
	}
	
	//�����ַ���
	public static char[] Stringinput()
	{
		System.out.println("���������[a,b,c]��ʶ����ַ�����");
		//���ַ������ַ�������
		Scanner sc = new Scanner(System.in);
		String str=sc.next();
		char cin[] = str.toCharArray();//����toCharArray����ת��
		sc.close();
		return cin;
	}
		
	//��ʼ��״̬ת�ƹ�ϵ
	public static ArrayList<State_trans> State_trans_init() {
		//���ܾ�״̬��NO��ʾ�����մ���#��ʾ
		ArrayList<State_trans> list = new ArrayList<State_trans>();
		list.add(new State_trans("q1",'#',"q2","#","$"));
		list.add(new State_trans("q2",'a',"q2","#","#"));
		list.add(new State_trans("q2",'b',"q2","#","b"));
		list.add(new State_trans("q2",'b',"q2","c","#"));
		list.add(new State_trans("q2",'c',"q3","b","#"));
		list.add(new State_trans("q2",'c',"q3","#","c"));
		list.add(new State_trans("q3",'a',"q3","#","#"));
		list.add(new State_trans("q3",'c',"q3","b","#"));
		list.add(new State_trans("q3",'c',"q3","#","c"));
		list.add(new State_trans("q3",'b',"q2","c","#"));
		list.add(new State_trans("q3",'b',"q2","#","b"));
		list.add(new State_trans("q2",'#',"q4","$","#"));
		list.add(new State_trans("q3",'#',"q4","$","#"));
		
		return list;
	}
	//���״̬ת�ƹ�ϵ
	public static void State_trans_print(ArrayList<State_trans> State_trans_list)
	{
		System.out.println("--------------------------------------------");
		System.out.println("-----------------״̬ת�ƹ�ϵ----------------");
		System.out.println("��ǰ״̬--�ַ�--ת��״̬--��ջԪ��--ѹջԪ��");
		for(Iterator iterator=State_trans_list.iterator();iterator.hasNext();)
		{
			State_trans tran=(State_trans)iterator.next();
			System.out.println(tran.getOldState()+"-------"+tran.getTrans()+"-------"+tran.getNewState()+"-------"+tran.getPopstr()+"-------"+tran.getPushstr());
		}
		
	}
	//�ж��ַ����Ƿ񱻽���
	public static boolean Stringaccept(char cin[],ArrayList<State_trans> State_trans_list,Stack<String> stack)
	{
		//��ʼ��״̬����
		String currentState="q1";
		//������ַ�����q1��q2
		currentState="q2";
		stack.push("$");
		//��ǰ�������α�����ȡ�ַ�
		for (int i = 0; i < cin.length; i++) {			
			System.out.println("--------------------------------------------");
			System.out.printf("ʶ���%d���ַ�%s",i+1,cin[i]);
			System.out.println();
			//��������
			for(State_trans stateTr : State_trans_list)
			{
				//�ҵ�״̬���ַ�ƥ���ת������
				if(stateTr.getOldState().equals(currentState)&&stateTr.getTrans()==cin[i])
					{
						//�����ַ���ջ��Ԫ�ز�ͬ��ջ���գ���ջ
						if(stateTr.getPopstr().equals(stack.peek())&&!stack.peek().equals(Character.toString(cin[i])))
						{
							stack.pop();
							if(!stateTr.getPushstr().equals("#"))
							{
								stack.push(stateTr.getPushstr());
							}
							currentState=stateTr.getNewState();
							break;
						}
						//�����ַ���ջ��Ԫ����ͬ��ջ�գ�ѹջ
						if(stateTr.getPopstr().equals("#"))
						{
							if(!stateTr.getPushstr().equals("#"))
							{
								stack.push(stateTr.getPushstr());
							}
							currentState=stateTr.getNewState();
							break;
						}	
					}
			}
			
			System.out.println("ʶ��ǰ�ַ�:"+cin[i]+",��״̬Ϊ:"+currentState);
			//���ջ��Ԫ��
			/*
			System.out.print("ջ��Ԫ��[");
			for(String str : stack)
			{
				System.out.print(str+" ");
			}
			System.out.println();
			*/
			System.out.println("--------------------------------------------");
		}
		//����״̬
		if(stack.peek().equals("$")&&(currentState.equals("q3")||currentState.equals("q2")))
		{
			currentState="q4";
			stack.pop();
			return true;
		}
		return false;
	}
}
