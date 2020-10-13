import java.util.Scanner;

public class hw4 
{
	static boolean debug = false;

	public static void main(String[] args)
	{
		Scanner scnr = new Scanner(System.in);
		String input = scnr.nextLine();
		
		while(!input.equals("Q"))
		{
			if(debug)
				System.out.println(input);
			
			char[] inputArr = input.toCharArray();
			Leave_Letters(inputArr);
			int[][] PTable = Longest_Palendrome(inputArr, input.length());
			
			if(debug)
				Print_Table(PTable);
			
			Generate_LPS(inputArr, PTable);
			
			input = scnr.nextLine();
		}

	
		scnr.close();
		System.out.println("Exited.");
	}

	
	// Methods
	
	public static int max(int a, int b)
	{
		if(a >= b)
			return a;
		return b;
	}
	
	public static void Generate_LPS(char[] s, int[][] P)
	{
		
		char[] pal = new char[P[0].length + 1];
		int i = 0, j = P[0].length - 1, k = 0;
		
		if(P[i][j] == 1)
		{
			System.out.println(s[0]);
			return;
		}
		
		
		while(P[i][j] != 0)
		{
			
			if((P[i][j] > P[i + 1][j]) && (P[i][j] > P[i][j - 1]))
			{
				pal[k] = s[j];
				i++;
				j--;
				k++;
				
				if(debug)
				{
					System.out.println("- " + pal[k - 1] + " added to pal");
					System.out.println("down and left");
				}
			}
			else if(P[i + 1][j] > P[i][j - 1])
			{
				i++;
				
				if(debug)
					System.out.println("down");
			}
			else
			{
				j--;
				
				if(debug)
					System.out.println("left");
			}
			
			
		}
		
		i = 0;
		while(pal[i] != '\0')
		{
			System.out.print(pal[i]);
			i++;
		}
		
		if(P[0][P[0].length - 1] % 2 == 1)
		{
			i--;
		}
		
		i--;
		
		while(i >= 0)
		{
			System.out.print(pal[i]);
			i--;
		}
		
		System.out.println();
		return;
	}
	
	public static int[][] Longest_Palendrome(char[] s, int len)
	{
		int[][] P = new int[len][len];
		
		for(int i = 0; i < len; i++)
		{
			P[i][i] = 1;
		}
		
		for(int dist = 2; dist <= len; dist++)
		{
			for(int i = 0; i < len - dist + 1; i++)
			{
				int j = i + dist - 1;
				
				if(s[i] == s[j] && dist == 2)
				{
					P[i][j] = 2;
				}
				else if(s[i] == s[j])
				{
					P[i][j] = P[i + 1][j - 1] + 2;
				}
				else
				{
					P[i][j] = max(P[i][j - 1], P[i + 1][j]);
				}
			}
		}
		
		return P;
	}
	
	public static void Print_Table(int[][] table)
	{
		int rows = table.length;
		int cols = table[0].length;
		
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				System.out.print("["+table[i][j]+"]");
			}
			System.out.println();
		}
	}
	
	public static void Print_Array(char[] s)
	{
		for(int i = 0; i < s.length; i++)
			System.out.print(s[i]);
	}
	
	public static void Leave_Letters(char[] s)
	{
		int offset = 0;
		for(int i = 0; i < s.length; i++)
		{
			if(s[i] < 65 || (s[i] > 90 && s[i] < 97) || s[i] > 122)
			{
				if(debug)
					System.out.println(s[i] + "is not a letter");
				
				offset++;
				continue;
			}
			s[i - offset] = s[i];
		}
		
		for(int i = offset; i > 0; i--)
		{
			s[s.length - i] = '\0';
		}
		
		if(debug)
		{
			Print_Array(s);
			System.out.println("all non letters removed");
		}
	}
}

