package com.example.action_bar;




import java.awt.*;

public class MainClass
{
//replace 0 with O
    public static String balance(String s) throws Exception
    {
        s = s.replaceAll("=",":");
        s = s.replaceAll("-->",":");
        s = s.replaceAll("->",":");
        int solns[][];
        chEquation newEqu;

        newEqu = new chEquation(s);
        solns = newEqu.Balance();
        //this can throw an error
        //it's to be caught by an error hander
        //in the function that calls this

        String output = "";
        String answ[] = new String[solns[0].length];
        for(int i = 0; i < solns[0].length; i++)
        {
            answ[i] = "";
            for(int j = 0; j < solns.length; j++)
            {
                if(solns[j][i] <= 0)
                    continue;
                if(solns[j][i] != 1)
                    answ[i] = answ[i] + solns[j][i] + newEqu.getItem(j).getSymbol() + " + ";
                else
                    answ[i] = answ[i] + newEqu.getItem(j).getSymbol() + " + ";
            }

            answ[i] = answ[i].substring(0, answ[i].length() - 3);
            answ[i] = answ[i] + " --> ";    //will be made \u2192 later


            for(int j = 0; j < solns.length; j++)
            {
                if(solns[j][i] >= 0)
                    continue;
                if(solns[j][i] != -1)
                    answ[i] = answ[i] + solns[j][i] * -1 + newEqu.getItem(j).getSymbol() + " + ";
                else
                    answ[i] = answ[i] + newEqu.getItem(j).getSymbol() + " + ";
            }

            answ[i] = answ[i].substring(0, answ[i].length() - 3);
            output += answ[i];
            output += "\n";

        }
        return output.substring(0,output.length()-1);
    }

}
