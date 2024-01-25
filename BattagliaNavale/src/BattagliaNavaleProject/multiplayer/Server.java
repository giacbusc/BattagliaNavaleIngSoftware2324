package BattagliaNavaleProject.multiplayer;
import org.zeromq.SocketType;
import org.zeromq.ZMQ;

import BattagliaNavaleProject.client.InfoBoat;
import BattagliaNavaleProject.client.Square;

import org.zeromq.ZContext;

public class Server
{
	private Square[][] player1;
	private Square[][] player2;
	private String[] spedire= new String[7];
	final static int MAX_LENGTH=9;
	private String[] mexprec = new String[3];
	
	public Server() throws Exception
	{
		try (ZContext context = new ZContext()) {
		      //  Socket to talk to clients
		      ZMQ.Socket socket = context.createSocket(SocketType.REP);
		      socket.bind("tcp://172.16.128.218:5555");

		      while (!Thread.currentThread().isInterrupted()) 
		      {
		        byte[] reply = socket.recv(0); //funz.rec bloccata
		       String messaggio=new String(reply, ZMQ.CHARSET);
		       //devo dividere il messaggio
		       String[] mexSplit=messaggio.split(",");
		       String x=mexSplit[0];
		       String y=mexSplit[1];
		       String nomeBarca=mexSplit[2];
		       InfoBoat boat = Enum.valueOf(InfoBoat.class, nomeBarca);
		       int l=boat.getLunghezza();
		       
		       
		         
		       if(mexprec[2].equals(nomeBarca))
		       {
		    	  //RIEMPI CELLE
		    	   riempiCelle(Integer.valueOf(x).intValue(),Integer.valueOf(y).intValue(),l,Integer.valueOf(mexprec[0]).intValue(),Integer.valueOf(mexprec[1]).intValue());
		       }
		       else
		       {
				   String fiocco = controllaCella(Integer.valueOf(x).intValue(),Integer.valueOf(y).intValue(),l);
				   //mandare fiocco
			   }
		       
		           
		         //LOGICA DI SPEDIZIONE DEL MEX
		         //nel mex prec salviamo x,y,nomebarca
		         mexprec[0]=x;
		         mexprec[1]=y;
		         mexprec[2]=nomeBarca;
		   
		      }
		  
		}
	}
		public String controllaCella(int x,int y, int l)
		{ 
			
			String a = Integer.toString(x);
			String b = Integer.toString(y);
			spedire[0] = a;
			spedire[1] = b;
			
			
		
			switch(l)
			{
			case 1:
				{
					if(cellaLibera(x,y)==true)
					{
						spedire[2] = "1";
					}
					break;
				}
			case 2:
				{
					
					boolean verificaPosizione=false;
					
					
					//**NORD** 
					if(checkFuoriGriglia(x,y,l,0))
					{
						if(cellaLibera(x,y-1)==true)
						{	
							spedire[3] = "0";
							spedire[2]="1";
						}
					}
					
					//**EST**
					if(checkFuoriGriglia(x,y,l,1))
					{
						if(cellaLibera(x+1,y)==true)
						{ 	//est							
							spedire[4]="0";
							spedire[2]="1";
						}	
					}
					
					//**SUD**
					if(checkFuoriGriglia(x,y,l,2))
					{
						if(cellaLibera(x,y+1)==true) 
						{
							spedire[5] = "0";
							spedire[2]="1";
						}
					}
					
					//**OVEST**
					if(checkFuoriGriglia(x,y,l,3))
					{
						if(cellaLibera(x-1,y)==true)
						{ 	//ovest
							spedire[6]="0";
							spedire[2]="1";
						}
					}
					
					
					
					break;
				}
					
			case 3:
					{
						int contaCelleVere=0;
						
						//**SUD**
						if(checkFuoriGriglia(x,y,l,2))
						{
							for(int i=y; i<=y+3; i++)
							{
								if(cellaLibera(x,i)==true)
								{
									contaCelleVere++;
								}
							}
							if(contaCelleVere==3)
							{
								spedire[5] = "0";
								spedire[2] = "1";
							}
							contaCelleVere=0; //azzero cosi posso riutilizzarlo per gli altri casi
						}
						//**NORD**
						if(checkFuoriGriglia(x,y,l,0))
						{
							for(int i=y; i>=y-3; i--)
							{
								if(cellaLibera(x,i)==true)
								{
									contaCelleVere++;
								}
							}
							if(contaCelleVere==3)
							{
								spedire[3] = "0";
								spedire[2] = "1";
								
							}
							contaCelleVere=0;
						}
						//**OVEST**
						if(checkFuoriGriglia(x,y,l,3))
						{
							for(int i=x; i>=x-3; i--)
							{
								if(cellaLibera(i,y)==true)
								{
									contaCelleVere++;
								}
							}
							if(contaCelleVere==3)
							{
								spedire[6] = "0";
								spedire[2] = "1";
								
							}
							contaCelleVere=0;
						}
						//**EST**
						if(checkFuoriGriglia(x,y,l,1))
						{
							for(int i=x; i<=x+3; i++)
							{
								if(cellaLibera(i,y)==true)
								{
									contaCelleVere++;
								}
							}
							if(contaCelleVere==3)
							{
								spedire[4] = "0";
								spedire[2] = "1";
								
							}
							contaCelleVere=0; //azzero cosi posso riutilizzarlo per gli altri casi
					   }
					}
						
						break;
					
			case 4:
					{
						break;
					}
			}
			
			
        // Iteriamo attraverso gli elementi dell'array
        
		StringBuilder composta = new StringBuilder();
        for (String e : spedire) 
        {
            // Aggiungiamo l'elemento alla StringBuilder con uno spazio
            composta.append(e).append(",");
        }
					
			String compostaFinale = composta.toString().trim();
			return compostaFinale;
	}
	
	public boolean cellaLibera(int x,int y)
	{
		if(player1[x][y].getStato()==0)
		{
			return true;
		}
		return false;
	}

	
	public boolean checkFuoriGriglia(int x, int y, int l, int d)
	{
		if(d==0) //nord
		{
			if(x-l-1<0)
				return false;
		}
		if(d==1) //est
		{
			if(y+(l-1)>MAX_LENGTH)
				return false;
		}

		if(d==2) //sud
		{
			if(x+(l-1)>MAX_LENGTH)
				return false;
		}

		if(d==3) //ovest
		{
			if(y-l-1<0)
				return false;
		}
		return true;
	}
	
	//riempire le celle dopo il secondo click
	public void riempiCelle(int x, int y,int l,int xp,int yp ) //x e y posizioni secondo click,mentre xp e yp posizioni primo click
	{ 
		if(x!=xp) //CASO NORD o SUD
		{
			if(xp<x) //caso nord
			{
				for(int i=xp-1;i>xp-l;i--)
				{
					//mando le  coordinate delle celle da colorare
					//esempio mex: spedire(i,yp,.....)	 ?una volta che mando mex al client funziona come un return e non va avanti il for?
				}
			}
			
			if(xp>x) //caso sud
			{
				for(int i=xp+1;i<xp+l;i++)
				{
					//mando le  coordinate delle celle da colorare
					//esempio mex: spedire(i,yp,.....)	 ?una volta che mando mex al client funziona come un return e non va avanti il for?
				}
			}
		}
		
		if(y!=yp) //EST O OVEST
		{
			if(yp<y) //EST
			{
				for(int i=yp-1;i>yp-l;i--)
				{
					//mando le  coordinate delle celle da colorare
					//esempio mex: spedire(xp,i,.....)	 ?una volta che mando mex al client funziona come un return e non va avanti il for?
				}
			}
			if(yp>y) //OVEST
			{
				for(int i=yp+1;i<yp+l;i++)
				{
					//mando le  coordinate delle celle da colorare
					//esempio mex: spedire(xp,i,.....)	 ?una volta che mando mex al client funziona come un return e non va avanti il for?
				}
			}
		}
		
	}
}

