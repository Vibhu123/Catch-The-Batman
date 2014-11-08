package com.example.action_bar;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

public class Trignometry {

	public static double solve(String s)
		{
			
			
		Calculable calc=null;
		try {
			calc = new ExpressionBuilder(s).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			double result1=calc.calculate();
			return result1;
		}
	private String solveTrignometric(String text)
	{
		String[] lines=null;
		String ans="";
		text=text.replaceAll("cosec", "df");
		lines=text.split("\r\n|\n|\r");
		char[] op=new char[256];
		for(int p=0;p<op.length;p++)
		{
			op[p]='a';
		}
		int k=0;
		for(int i=0;i<lines.length;i++)
		{
			for(int j=0;j<lines[i].length();j++)
			{
				if(lines[i].charAt(j)=='+'||lines[i].charAt(j)=='-'||lines[i].charAt(j)=='*'||lines[i].charAt(j)=='/'||lines[i].charAt(j)=='%'
						)
				{
					op[k]=lines[i].charAt(j);
					k++;
				}
			}
		}
		int l=0;
		for(int i=0;i<lines.length;i++)
		{
			String[] tokens=lines[i].split("[-+*/=]");
			
			for(int j=0;j<tokens.length;j++)
			{
				if(tokens[j].contains("^"))
				{
					String tok[]=tokens[j].split("^");
					for(int k1=0;k1<tok.length;k1++)
					{
						if(tok[k1].contains("sin"))
						{
							tokens[j]=tokens[j].replaceAll("sin\\d+\\^\\d+","("+"sin"+"("+Math.toRadians(Double.valueOf(tok[k1].split("sin")[1].split("\\^")[0]))+")"+")"+"^"+tok[k1].split("\\^")[1]);
							if(op[l]!='a')
							{
								ans=ans+tokens[j]+op[l];
								l++;
							}
							else
								ans=ans+tokens[j];
							
						}
						if(tok[k1].contains("cos"))
						{
							tokens[j]=tokens[j].replaceAll("cos\\d+\\^\\d+","("+"cos"+"("+Math.toRadians(Double.valueOf(tok[k1].split("cos")[1].split("\\^")[0]))+")"+")"+"^"+tok[k1].split("\\^")[1]);
							if(op[l]!='a')
								ans=ans+tokens[j]+op[l];
							else
								ans=ans+tokens[j];
							l++;
						}
						if(tok[k1].contains("tan"))
						{
							tokens[j]=tokens[j].replaceAll("tan\\d+\\^\\d+","("+"tan"+"("+Math.toRadians(Double.valueOf(tok[k1].split("tan")[1].split("\\^")[0]))+")"+")"+"^"+tok[k1].split("\\^")[1]);
							if(op[l]!='a')
								ans=ans+tokens[j]+op[l];
							else
								ans=ans+tokens[j];
							l++;
						}
						if(tok[k1].contains("cot"))
						{
							tokens[j]=tokens[j].replaceAll("cot\\d+\\^\\d+","1/"+"("+"tan"+"("+Math.toRadians(Double.valueOf(tok[k1].split("cot")[1].split("\\^")[0]))+")"+")"+"^"+tok[k1].split("\\^")[1]);
							if(op[l]!='a')
								ans=ans+tokens[j]+op[l];
							else
								ans=ans+tokens[j];
							l++;
						}
						if(tok[k1].contains("df"))
						{
							tokens[j]=tokens[j].replaceAll("df\\d+\\^\\d+","1/"+"("+"sin"+"("+Math.toRadians(Double.valueOf(tok[k1].split("df")[1].split("\\^")[0]))+")"+")"+"^"+tok[k1].split("\\^")[1]);
							if(op[l]!='a')
								ans=ans+tokens[j]+op[l];
							else
								ans=ans+tokens[j];
							l++;
						}
						if(tok[k1].contains("sec"))
						{
							tokens[j]=tokens[j].replaceAll("sec\\d+\\^\\d+","1/"+"("+"cos"+"("+Math.toRadians(Double.valueOf(tok[k1].split("sec")[1].split("\\^")[0]))+")"+")"+"^"+tok[k1].split("\\^")[1]);
							if(op[l]!='a')
								ans=ans+tokens[j]+op[l];
							else
								ans=ans+tokens[j];
							l++;
						}
						if(tok[k1].matches("[0-9]+"))
						{
							ans=ans+tok[k1];
						}
					}
				}
				else
				{
					if(tokens[j].contains("sin"))
					{
						tokens[j]=tokens[j].replaceAll("sin\\d+","sin"+"("+Math.toRadians(Double.valueOf(tokens[j].split("sin")[1]))+")");
						if(op[l]!='a')
							ans=ans+tokens[j]+op[l];
						else
							ans=ans+tokens[j];
						l++;
					}
					else if(tokens[j].contains("cos"))
					{
						tokens[j]=tokens[j].replaceAll("cos\\d+", "cos"+"("+Math.toRadians(Double.valueOf(tokens[j].split("cos")[1]))+")");
						if(op[l]!='a')
							ans=ans+tokens[j]+op[l];
						else
							ans=ans+tokens[j];
						l++;
					}
					else if(tokens[j].contains("tan"))
					{
						tokens[j]=tokens[j].replaceAll("tan\\d+","tan"+"("+Math.toRadians(Double.valueOf(tokens[j].split("tan")[1]))+")");
						if(op[l]!='a')
							ans=ans+tokens[j]+op[l];
						else
							ans=ans+tokens[j];
						l++;
					}
					else if(tokens[j].contains("cot"))
					{
						tokens[j]=tokens[j].replaceAll("cot\\d+","1/"+"tan"+"("+Math.toRadians(Double.valueOf(tokens[j].split("cot")[1]))+")");
						if(op[l]!='a')
							ans=ans+tokens[j]+op[l];
						else
							ans=ans+tokens[j];
						l++;
					}
					else if(tokens[j].contains("df"))
					{
						tokens[j]=tokens[j].replaceAll("df\\d+","1/"+"sin"+"("+Math.toRadians(Double.valueOf(tokens[j].split("df")[1]))+")");
						if(op[l]!='a')
							ans=ans+tokens[j]+op[l];
						else
							ans=ans+tokens[j];
						l++;
					}
					else if(tokens[j].contains("sec"))
					{
						tokens[j]=tokens[j].replaceAll("sec\\d+","1/"+"cos"+"("+Math.toRadians(Double.valueOf(tokens[j].split("sec")[1]))+")");
						if(op[l]!='a')
							ans=ans+tokens[j]+op[l];
						else
							ans=ans+tokens[j];
						l++;
					}
					else if(tokens[j].matches("[0-9]+"))
					{
						ans=ans+tokens[j];
					}
				}
			}
		}
	
		return ans;
	}
	public static String main(String text)
	{
		Trignometry t1=new Trignometry();
		String a=t1.solveTrignometric(text);
		double ans=Math.round(t1.solve(a)*100.0)/100.0;
		return String.valueOf(ans);
	}
}

