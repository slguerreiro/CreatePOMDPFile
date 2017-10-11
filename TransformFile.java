import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TransformFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String[] S ={"S01","S02","S03","S04","S05","S06","S07","S08","S09","S10","S11","S12","S13","S14","S15","S16","S17","S18","S19","S20","S21","S22","S23","S24","S25","S26","S27","S28","S29","S30","S31","S32","S33","S34","S35","S36","S37","S38","S39","S40"};
		String[] A ={"no-op" , "cancel-client-order" , "request-more-ingredients","send-quality-questionnaire"};
		String[] Z= {"product-delivered","complaint","stock-break","transport-disrupt","running-ok"};
		
		PrintWriter ficheiroT, ficheiroO, ficheiroTudo;
		try {
			ficheiroT = new PrintWriter(new File("T.txt"));
			ficheiroO = new PrintWriter(new File("O.txt"));
			ficheiroTudo = new PrintWriter(new File("All.txt"));
			String texto;		
			float probabilidade=0;
		
			//T : A * S * S
			//exemplo:  T: no-op : S01 : * 0.025
			for (int iA = 0; iA < A.length ; iA++)
			{
				for (int iS1 = 0; iS1 < S.length ; iS1++)
				{
					System.out.print("T:" + iS1 + ": ");
					for (int iS2 = 0; iS2 < S.length ; iS2++)
					{	
						texto = "T: " + A[iA] + " : " + S[iS1] + " : " +  S[iS2] + "  ";
						
						switch (A[iA])
						{						
							case "send-quality-questionnaire":
								/*if ( (iS1 >= 0 && iS1 < 19)  ||
									 (iS1 >= 20 && iS1 < 24) ||
									 (iS1 >= 25 && iS1 < 34) ||
									 (iS1 >= 35 && iS1 < 39) )																				
								{
									if (iS2-iS1 == 1) probabilidade = (float) 0.95;
									else probabilidade = (float) (0.05 / (S.length-1));
								}
								else if (iS1 == 19 && iS2 == 0) probabilidade = (float) 0.95;
								else if (iS1 == 24 && iS2 == 20) probabilidade = (float) 0.95;
								else if (iS1 == 34 && iS2 == 25) probabilidade = (float) 0.95;
								else if (iS1 == 39 && iS2 == 25) probabilidade = (float) 0.95;
								else probabilidade = (float) (0.05 / (S.length-1));										
								break;*/
								if (iS1 == 18 && iS2 == 19) probabilidade = (float) 0.5;
								else if (iS1 == 38 && iS2 == 39) probabilidade = (float) 0.5;
								else if (iS1 == 18 && iS2 != 19) probabilidade = (float) (0.5 / (S.length-1));
								else if (iS1 == 38 && iS2 != 39) probabilidade = (float) (0.5 / (S.length-1));
								else probabilidade = (float) (1.0 / (S.length));
								break;
							case "no-op": 
									if ( (iS1 >= 0 && iS1 < 19)  ||
										 (iS1 >= 20 && iS1 < 24) ||
										 (iS1 >= 25 && iS1 < 34) ||
										 (iS1 >= 35 && iS1 < 39) )																				
									{
										if (iS2-iS1 == 1) probabilidade = (float) 0.95;
										else probabilidade = (float) (0.05 / (S.length-1));
									}
									else if (iS1 == 19 && iS2 == 0) probabilidade = (float) 0.95;
									else if (iS1 == 24 && iS2 == 20) probabilidade = (float) 0.95;
									else if (iS1 == 34 && iS2 == 25) probabilidade = (float) 0.95;
									else if (iS1 == 39 && iS2 == 25) probabilidade = (float) 0.95;
									else probabilidade = (float) (0.05 / (S.length-1));										
									break;
							case "cancel-client-order":
									if ( (iS1 >= 0 && iS1 <= 19) )
									{
										if (iS2 == 0)  probabilidade = (float) 0.9;
										else probabilidade = (float) (0.1 / (S.length-1));
									}
									else if ( (iS1 >= 20 && iS1 < 24) ||
											  (iS1 >= 25 && iS1 < 34) ||
											  (iS1 >= 35 && iS1 < 39) )																				
									{
										if (iS2-iS1 == 1) probabilidade = (float) 0.9;
										else probabilidade = (float) (0.1 / (S.length-1));
									}									
									else if (iS1 == 24 && iS2 == 20) probabilidade = (float) 0.9;
									else if (iS1 == 34 && iS2 == 25) probabilidade = (float) 0.9;
									else if (iS1 == 39 && iS2 == 25) probabilidade = (float) 0.9;
									else probabilidade = (float) (0.1 / (S.length-1));										
									break;								
							case "request-more-ingredients":
									if ( (iS1 >= 0 && iS1 <= 19) )
									{
										if (iS2 == 25)  probabilidade = (float) 0.75;
										else probabilidade = (float) (0.25 / (S.length-1));
									}
									else if ( (iS1 >= 20 && iS1 < 24) ||
											  (iS1 >= 25 && iS1 < 34) ||
											  (iS1 >= 35 && iS1 < 39) )																				
									{
										if (iS2-iS1 == 1) probabilidade = (float) 0.75;
										else probabilidade = (float) (0.25 / (S.length-1));
									}									
									else if (iS1 == 24 && iS2 == 20) probabilidade = (float) 0.75;
									else if (iS1 == 34 && iS2 == 25) probabilidade = (float) 0.75;
									else if (iS1 == 39 && iS2 == 25) probabilidade = (float) 0.75;
									else probabilidade = (float) (0.25 / (S.length-1));										
									break;								
						}
						
						System.out.print(probabilidade + " ");
						texto = texto + probabilidade;	
						ficheiroT.println(texto);
						ficheiroTudo.println(texto);						
					}
					System.out.println();
				}
			}
			ficheiroT.close();
			ficheiroTudo.println("\n\n\n");

			
			//O : A * S * Z 
			//exemplo:  O:   no-op : exec-T1-OK : product-delivered  0.98
			for (int iA = 0; iA < A.length ; iA++)
			{
				for (int iS = 0; iS < S.length ; iS++)
				{
					System.out.print("O:" + iS + ": ");
					for (int iZ = 0; iZ < Z.length ; iZ++)
					{	
						texto = "O: " + A[iA] + " : " + S[iS] + " : " +  Z[iZ] + "  ";
		
						if (	 S[iS] == "S20" &&  Z[iZ] == "product-delivered") probabilidade = (float) 0.70;								
						else if (S[iS] == "S20" &&  Z[iZ] == "stock-break") probabilidade = (float) 0.10;
						else if (S[iS] == "S20" &&  Z[iZ] == "complaint") probabilidade = (float) 0.10;
						else if (S[iS] == "S20") probabilidade = (float) 0.05;
						
						else if (S[iS] == "S40" &&  Z[iZ] == "product-delivered") probabilidade = (float) 0.70;
						else if (S[iS] == "S40" &&  Z[iZ] == "complaint" ) probabilidade = (float) 0.10;
						else if (S[iS] == "S40" &&  Z[iZ] == "stock-break" ) probabilidade = (float) 0.10;
						else if (S[iS] == "S40") probabilidade = (float) 0.05;
						
						else if (S[iS] == "S23" &&  Z[iZ] == "transport-disrupt") probabilidade = (float) 0.20;  // T6/execute									
						else if (S[iS] == "S23") probabilidade = (float) (0.80 / (Z.length-1));
						
						
						else if (Z[iZ] == "running-ok" ) probabilidade = (float) (0.95);
						else probabilidade = (float) (0.05 / (Z.length-1));
						
						/* Alteracao em 2014.02.24
						 * 
						 * else probabilidade = (float) (1.0 / (Z.length));*/			
						
						
						System.out.print(probabilidade + " ");
						texto = texto + probabilidade;	
						ficheiroO.println(texto);
						ficheiroTudo.println(texto);						
					}				
					System.out.println();
				}
			}
			ficheiroO.close();
			ficheiroTudo.close();
	
		
		} 
		catch (FileNotFoundException e) 
		{	
			e.printStackTrace(); 
		}
			
			
		

	}

}
