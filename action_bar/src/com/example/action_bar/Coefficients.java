package com.example.action_bar;

import android.util.Log;

public class Coefficients {
public static double[] extractMatrix(String [] vars ,String eqn) {
		
		String arr[]=new String[vars.length];
		double coeffs[]=new double[vars.length];
		int i;
		for(i=0;i<arr.length;i++)
			arr[i]="0";
		eqn=eqn.substring(0, eqn.indexOf("="));
		eqn=eqn.replaceAll("[-]","+-");
		String[] constants=eqn.split("[+]");
		int j;
	for(i=0;i<constants.length;i++)
		{
			for(j=0;j<vars.length;j++)
			{
				if((constants[i].contains(vars[j])))
				{
					if(constants[i].equals("-"+vars[j]))
					{
						arr[j]=arr[j]+"-1";
					}
					else if(constants[i].equals(vars[j]))
					{
						arr[j]=arr[j]+"+1";
					}
					else
					{
						String z=constants[i].substring(0,constants[i].indexOf(vars[j]));
						if(z.contains("-"))
								arr[j]=arr[j]+z;
						else	
						arr[j]=arr[j]+"+"+z;
					}
				}
				
			}
		}
	for(int j1=0;j1<arr.length;j1++)
	{
		Log.v("arr",arr[j1]);
	}
	int k=0;
	for(k=0;k<arr.length;k++)
	{
		coeffs[k]=Expression.solve(arr[k]);
		Log.v("co",""+coeffs[k]);
	}
	return coeffs;
}
}

