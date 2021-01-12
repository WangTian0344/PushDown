
public class State_trans {
	private String oldState;
	private char trans;
	private String newState;
	private String popstr;
	private String pushstr;
	State_trans(String oldState,char trans,String newState,String popstr,String pushstr)
	{
		this.oldState=oldState;
		this.trans=trans;
		this.newState=newState;
		this.popstr=popstr;
		this.pushstr=pushstr;
	}
	public String getOldState()
	{
		return this.oldState;
	}
	public void setOldState(String oldState)
	{
		this.oldState=oldState;
	}
	public String getNewState()
	{
		return this.newState;
	}
	public void setNewState(String newState)
	{
		this.oldState=newState;
	}
	public char getTrans()
	{
		return this.trans;
	}
	public void setTrans(char trans)
	{
		this.trans=trans;
	}
	public String getPopstr()
	{
		return this.popstr;
	}
	public void setPopstr(String popstr)
	{
		this.popstr=popstr;
	}
	public String getPushstr()
	{
		return this.pushstr;
	}
	public void setPushstr(String pushstr)
	{
		this.pushstr=pushstr;
	}
	
}
