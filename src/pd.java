import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class pd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char cin[]=Stringinput();
		//初始化状态转移集合
		ArrayList<State_trans> State_trans_list=State_trans_init();
		//栈
		Stack<String> stack=new Stack<String>();
		
		//State_trans_print(State_trans_list);
		if(Stringaccept(cin,State_trans_list,stack))
			System.out.println("字符串被下推自动机接受!");
		else
			System.out.println("字符串不被下推自动机接受!");
	}
	
	//接收字符串
	public static char[] Stringinput()
	{
		System.out.println("请输入仅含[a,b,c]待识别的字符串：");
		//将字符存入字符数组中
		Scanner sc = new Scanner(System.in);
		String str=sc.next();
		char cin[] = str.toCharArray();//利用toCharArray方法转换
		sc.close();
		return cin;
	}
		
	//初始化状态转移关系
	public static ArrayList<State_trans> State_trans_init() {
		//将拒绝状态用NO表示，将空串用#表示
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
	//输出状态转移关系
	public static void State_trans_print(ArrayList<State_trans> State_trans_list)
	{
		System.out.println("--------------------------------------------");
		System.out.println("-----------------状态转移关系----------------");
		System.out.println("当前状态--字符--转移状态--弹栈元素--压栈元素");
		for(Iterator iterator=State_trans_list.iterator();iterator.hasNext();)
		{
			State_trans tran=(State_trans)iterator.next();
			System.out.println(tran.getOldState()+"-------"+tran.getTrans()+"-------"+tran.getNewState()+"-------"+tran.getPopstr()+"-------"+tran.getPushstr());
		}
		
	}
	//判断字符串是否被接受
	public static boolean Stringaccept(char cin[],ArrayList<State_trans> State_trans_list,Stack<String> stack)
	{
		//初始化状态对象
		String currentState="q1";
		//读入空字符，从q1到q2
		currentState="q2";
		stack.push("$");
		//从前到后依次遍历读取字符
		for (int i = 0; i < cin.length; i++) {			
			System.out.println("--------------------------------------------");
			System.out.printf("识别第%d个字符%s",i+1,cin[i]);
			System.out.println();
			//遍历集合
			for(State_trans stateTr : State_trans_list)
			{
				//找到状态与字符匹配的转移条件
				if(stateTr.getOldState().equals(currentState)&&stateTr.getTrans()==cin[i])
					{
						//读入字符与栈顶元素不同且栈不空，弹栈
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
						//读入字符与栈顶元素相同或栈空，压栈
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
			
			System.out.println("识别当前字符:"+cin[i]+",新状态为:"+currentState);
			//输出栈内元素
			/*
			System.out.print("栈内元素[");
			for(String str : stack)
			{
				System.out.print(str+" ");
			}
			System.out.println();
			*/
			System.out.println("--------------------------------------------");
		}
		//接受状态
		if(stack.peek().equals("$")&&(currentState.equals("q3")||currentState.equals("q2")))
		{
			currentState="q4";
			stack.pop();
			return true;
		}
		return false;
	}
}
