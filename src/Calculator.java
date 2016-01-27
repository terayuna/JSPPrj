
public class Calculator {
	public static int div(int a, int b) throws ZeroChoice
	{
		if(b==0)
		{
			throw new ZeroChoice();
		}
		return a/b;
	}
	public static int mul(int a,int b)
	{
		return a*b;
	}
}
 