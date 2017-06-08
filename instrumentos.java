import sintese.*;
import javax.sound.midi.*;

class instrumentos{
	   
    // public static String[][] instru1 = new String[4][];
    public static String[][] instru1 = { { "1", "1", "0.0", "0", "10" },
                                         { "2", "2", "0.0", "0", "5" },
                                         { "3", "3", "0.0", "0", "3" },
                                         { "Ganho Total", "-", "-", "-", "4" },
                                        };
    public static String[][] instru2 =    { { "1", "1", "0.0", "0", "10" },
								            { "2", "5", "0.0", "0", "5" },
								            { "3", "9", "0.0", "0", "3" },
								            { "Ganho Total", "-", "-", "-", "4" },
								           };
	public static String[][] instru3 =    { { "1", "1", "0.0", "0", "4" },
								            { "2", "5", "0.0", "0", "5" },
								            { "3", "9", "0.0", "0", "8" },
								            { "Ganho Total", "-", "-", "-", "4" },
								           };
	public static String[][] instru4 =    { { "1", "1", "0.0", "0", "3" },
								            { "2", "3", "0.0", "0", "5" },
								            { "3", "4", "0.0", "0", "8" },
								            { "Ganho Total", "-", "-", "-", "4" },
								           };
	public static String[][] instru5 =    { { "1", "1", "0.0", "0", "10" },
								            { "2", "2", "0.0", "0", "5" },
								            { "3", "3", "0.0", "0", "3" },
								            { "Ganho Total", "-", "-", "-", "4" },
								           };
	public static String[][] instru6 =    { { "1", "1", "0.0", "0", "10" },
								            { "2", "5", "0.0", "0", "5" },
								            { "3", "9", "0.0", "0", "3" },
								            { "Ganho Total", "-", "-", "-", "4" },
								           };
	public static String[][] instru7 =    { { "1", "1", "0.0", "0", "4" },
								            { "2", "5", "0.0", "0", "5" },
								            { "3", "9", "0.0", "0", "8" },
								            { "Ganho Total", "-", "-", "-", "4" },
								           };
	public static String[][] instru8 =    { { "1", "1", "0.0", "0", "3" },
								            { "2", "3", "0.0", "0", "5" },
								            { "3", "4", "0.0", "0", "8" },
								            { "Ganho Total", "-", "-", "-", "4" },
								           };

    
	public static Dispositivo instrumento1(){
		Curva curva1, curva2, curva3;
		float ganho1, ganho2, ganho3, ganhoTotal;
		   float h1, h2, h3;
		   float lambda1, lambda2, lambda3;
		   float fase1, fase2, fase3;
		   
		   Envoltoria   env1, env2, env3;
		   
		   InstrumentoAditivo ins;
		UnidadeH uh1, uh2, uh3;
		
		ganho1 = 10;
		ganho2 = 5;
		ganho3 = 3;
		ganhoTotal = 4;
		
		h1 = 1;
		h2 = 2;
		h3 = 3;
		
		lambda1 = 0f;
		lambda2 = 0f;
		lambda3 = 0f;
		
		fase1 = 0;
		fase2 = 0;
		fase3 = 0;
		
		env1 = new Envoltoria();   
	    env2 = new Envoltoria();   
	    env3 = new Envoltoria();   
	  
	    uh1 = new UnidadeH();
	    uh2 = new UnidadeH();
	    uh3 = new UnidadeH();
	    
	    curva1 = new Curva(720);     
	    curva1.addPonto(  0f, 20f);
	    curva1.addPonto(72f, 1500f);
	    curva1.addPonto(400f, 200f);
	    curva1.addPonto(720f, 0f);     
	
	    curva2 = new Curva(720);     
	    curva2.addPonto(0f, 30f);   
	    curva2.addPonto(50f, 2000f);
	    curva2.addPonto(550f, 500f);
	    curva2.addPonto(720f, 0f);     
	
	    curva3 = new Curva(720);     
	    curva3.addPonto(0f, 40f);   
	    curva3.addPonto(80f, 1000f);
	    curva3.addPonto(600f, 100f);
	    curva3.addPonto(720f, 0f);
	    
	    env1.setCURVA(curva1);      
	    env2.setCURVA(curva2);    
	    env3.setCURVA(curva3);
	    
	    uh1.setEnvoltoria(env1);
	    uh1.setH(h1);
	    uh1.setLambda(lambda1);
	    uh1.setFase(fase1);
	    uh1.setGanho(ganho1);
            instru1[0][0] = String.valueOf( h1 );
            instru1[0][1] = String.valueOf( lambda1 );
            instru1[0][2] = String.valueOf( fase1 );
            instru1[0][3] = String.valueOf( ganho1 );

	    uh2.setEnvoltoria(env2);
	    uh2.setH(h2);
	    uh2.setLambda(lambda2);
	    uh2.setFase(fase2);
	    uh2.setGanho(ganho2);
            instru1[1][0] = String.valueOf( h2 );
            instru1[1][1] = String.valueOf( lambda2 );
            instru1[1][2] = String.valueOf( fase2 );
            instru1[1][3] = String.valueOf( ganho2 );
	     
	    uh3.setEnvoltoria(env3);
	    uh3.setH(h3);
	    uh3.setLambda(lambda3);
	    uh3.setFase(fase3);
	    uh3.setGanho(ganho3);
            instru1[2][0] = String.valueOf( h3 );
            instru1[2][1] = String.valueOf( lambda3 );
            instru1[2][2] = String.valueOf( fase3 );
            instru1[2][3] = String.valueOf( ganho3 );
            
            instru1[3][0] = "Ganho Total";
            instru1[3][1] = "";
            instru1[3][2] = "";
            instru1[3][3] = String.valueOf( ganhoTotal );

	    ins = new InstrumentoAditivo();

	    ins.addUnidade(uh1);
	    ins.addUnidade(uh2);
	    ins.addUnidade(uh3);
	    ins.setGanho(ganhoTotal);
	    
	    return ins;
	}

	public static Dispositivo instrumento2(){
		Curva curva1, curva2, curva3;
		float ganho1, ganho2, ganho3, ganhoTotal;
		   float h1, h2, h3;
		   float lambda1, lambda2, lambda3;
		   float fase1, fase2, fase3;
		   
		   Envoltoria   env1, env2, env3;
		   
		   InstrumentoAditivo ins;
		   UnidadeH uh1, uh2, uh3;
			
		ganho1 = 10;
		ganho2 = 5;
		ganho3 = 3;
		ganhoTotal = 4;
		
		h1 = 1;
		h2 = 5;
		h3 = 9;
		
		lambda1 = 0f;
		lambda2 = 0f;
		lambda3 = 0f;
		
		fase1 = 0;
		fase2 = 0;
		fase3 = 0;
		
		env1 = new Envoltoria();   
	    env2 = new Envoltoria();   
	    env3 = new Envoltoria();   
	  
	    uh1 = new UnidadeH();
	    uh2 = new UnidadeH();
	    uh3 = new UnidadeH();
	    
	    curva1 = new Curva(720);     
	    curva1.addPonto(  0f, 20f);
	    curva1.addPonto(72f, 1500f);
	    curva1.addPonto(400f, 200f);
	    curva1.addPonto(720f, 0f);     
	
	    curva2 = new Curva(720);     
	    curva2.addPonto(0f, 30f);   
	    curva2.addPonto(50f, 2000f);
	    curva2.addPonto(550f, 500f);
	    curva2.addPonto(720f, 0f);     
	
	    curva3 = new Curva(720);     
	    curva3.addPonto(0f, 40f);   
	    curva3.addPonto(80f, 1000f);
	    curva3.addPonto(600f, 100f);
	    curva3.addPonto(720f, 0f);
	    
	    env1.setCURVA(curva1);      
	    env2.setCURVA(curva2);    
	    env3.setCURVA(curva3);
	    
	    uh1.setEnvoltoria(env1);
	    uh1.setH(h1);
	    uh1.setLambda(lambda1);
	    uh1.setFase(fase1);
	    uh1.setGanho(ganho1);
            instru2[0][0] = String.valueOf( h1 );
            instru2[0][1] = String.valueOf( lambda1 );
            instru2[0][2] = String.valueOf( fase1 );
            instru2[0][3] = String.valueOf( ganho1 );

	    uh2.setEnvoltoria(env2);
	    uh2.setH(h2);
	    uh2.setLambda(lambda2);
	    uh2.setFase(fase2);
	    uh2.setGanho(ganho2);
            instru2[1][0] = String.valueOf( h2 );
            instru2[1][1] = String.valueOf( lambda2 );
            instru2[1][2] = String.valueOf( fase2 );
            instru2[1][3] = String.valueOf( ganho2 );
	     
	    uh3.setEnvoltoria(env3);
	    uh3.setH(h3);
	    uh3.setLambda(lambda3);
	    uh3.setFase(fase3);
	    uh3.setGanho(ganho3);
            instru2[2][0] = String.valueOf( h3 );
            instru2[2][1] = String.valueOf( lambda3 );
            instru2[2][2] = String.valueOf( fase3 );
            instru2[2][3] = String.valueOf( ganho3 );
            
            instru2[3][0] = "Ganho Total";
            instru2[3][1] = "";
            instru2[3][2] = "";
            instru2[3][3] = String.valueOf( ganhoTotal );

	    ins = new InstrumentoAditivo();

	    ins.addUnidade(uh1);
	    ins.addUnidade(uh2);
	    ins.addUnidade(uh3);
	    ins.setGanho(ganhoTotal);
	    
	    return ins;
	}

	public static Dispositivo instrumento3(){
		Curva curva1, curva2, curva3;
		float ganho1, ganho2, ganho3, ganhoTotal;
		   float h1, h2, h3;
		   float lambda1, lambda2, lambda3;
		   float fase1, fase2, fase3;
		   
		   Envoltoria   env1, env2, env3;
		   
		   InstrumentoAditivo ins;
		   
		   UnidadeH uh1, uh2, uh3;
			
		ganho1 = 4;
		ganho2 = 5;
		ganho3 = 8;
		ganhoTotal = 4;
		
		h1 = 1;
		h2 = 5;
		h3 = 9;
		
		lambda1 = 0f;
		lambda2 = 0f;
		lambda3 = 0f;
		
		fase1 = 0;
		fase2 = 0;
		fase3 = 0;
		
		env1 = new Envoltoria();   
	    env2 = new Envoltoria();   
	    env3 = new Envoltoria();   
	  
	    uh1 = new UnidadeH();
	    uh2 = new UnidadeH();
	    uh3 = new UnidadeH();
	    
	    curva1 = new Curva(720);     
	    curva1.addPonto(  0f, 20f);
	    curva1.addPonto(72f, 1500f);
	    curva1.addPonto(400f, 200f);
	    curva1.addPonto(720f, 0f);     
	
	    curva2 = new Curva(720);     
	    curva2.addPonto(0f, 30f);   
	    curva2.addPonto(50f, 2000f);
	    curva2.addPonto(550f, 500f);
	    curva2.addPonto(720f, 0f);     
	
	    curva3 = new Curva(720);     
	    curva3.addPonto(0f, 40f);   
	    curva3.addPonto(80f, 1000f);
	    curva3.addPonto(600f, 100f);
	    curva3.addPonto(720f, 0f);
	    
	    env1.setCURVA(curva1);      
	    env2.setCURVA(curva2);    
	    env3.setCURVA(curva3);
	    
	    uh1.setEnvoltoria(env1);
	    uh1.setH(h1);
	    uh1.setLambda(lambda1);
	    uh1.setFase(fase1);
	    uh1.setGanho(ganho1);

	    uh2.setEnvoltoria(env2);
	    uh2.setH(h2);
	    uh2.setLambda(lambda2);
	    uh2.setFase(fase2);
	    uh2.setGanho(ganho2);
	     
	    uh3.setEnvoltoria(env3);
	    uh3.setH(h3);
	    uh3.setLambda(lambda3);
	    uh3.setFase(fase3);
	    uh3.setGanho(ganho3);

	    ins = new InstrumentoAditivo();

	    ins.addUnidade(uh1);
	    ins.addUnidade(uh2);
	    ins.addUnidade(uh3);
	    ins.setGanho(ganhoTotal);
	    
	    return ins;
	}

	public static Dispositivo instrumento4(){
		Curva curva1, curva2, curva3;
		float ganho1, ganho2, ganho3, ganhoTotal;
		   float h1, h2, h3;
		   float lambda1, lambda2, lambda3;
		   float fase1, fase2, fase3;
		   
		   Envoltoria   env1, env2, env3;
		   
		   InstrumentoAditivo ins;

		   UnidadeH uh1, uh2, uh3;
			
		ganho1 = 3;
		ganho2 = 5;
		ganho3 = 8;
		ganhoTotal = 4;
		
		h1 = 1;
		h2 = 3;
		h3 = 4;
		
		lambda1 = 0f;
		lambda2 = 0f;
		lambda3 = 0f;
		
		fase1 = 0;
		fase2 = 0;
		fase3 = 0;
		
		env1 = new Envoltoria();   
	    env2 = new Envoltoria();   
	    env3 = new Envoltoria();   
	  
	    uh1 = new UnidadeH();
	    uh2 = new UnidadeH();
	    uh3 = new UnidadeH();
	    
	    curva1 = new Curva(720);     
	    curva1.addPonto(  0f, 20f);
	    curva1.addPonto(72f, 1500f);
	    curva1.addPonto(400f, 200f);
	    curva1.addPonto(720f, 0f);     
	
	    curva2 = new Curva(720);     
	    curva2.addPonto(0f, 30f);   
	    curva2.addPonto(50f, 2000f);
	    curva2.addPonto(550f, 500f);
	    curva2.addPonto(720f, 0f);     
	
	    curva3 = new Curva(720);     
	    curva3.addPonto(0f, 40f);   
	    curva3.addPonto(80f, 1000f);
	    curva3.addPonto(600f, 100f);
	    curva3.addPonto(720f, 0f);
	    
	    env1.setCURVA(curva1);      
	    env2.setCURVA(curva2);    
	    env3.setCURVA(curva3);
	    
	    uh1.setEnvoltoria(env1);
	    uh1.setH(h1);
	    uh1.setLambda(lambda1);
	    uh1.setFase(fase1);
	    uh1.setGanho(ganho1);

	    uh2.setEnvoltoria(env2);
	    uh2.setH(h2);
	    uh2.setLambda(lambda2);
	    uh2.setFase(fase2);
	    uh2.setGanho(ganho2);
	     
	    uh3.setEnvoltoria(env3);
	    uh3.setH(h3);
	    uh3.setLambda(lambda3);
	    uh3.setFase(fase3);
	    uh3.setGanho(ganho3);

	    ins = new InstrumentoAditivo();

	    ins.addUnidade(uh1);
	    ins.addUnidade(uh2);
	    ins.addUnidade(uh3);
	    ins.setGanho(ganhoTotal);
	    
	    return ins;
	}
	
	public static Dispositivo instrumento5(){
		Curva curva1, curva2, curva3;
		float ganho1, ganho2, ganho3, ganhoTotal;
		   float h1, h2, h3;
		   float lambda1, lambda2, lambda3;
		   float fase1, fase2, fase3;
		   
		   Envoltoria   env1, env2, env3;
		   
		   InstrumentoAditivo ins;

		   UnidadeH uh1, uh2, uh3;
			
		ganho1 = 10;
		ganho2 = 5;
		ganho3 = 3;
		ganhoTotal = 4;
		
		h1 = 1;
		h2 = 2;
		h3 = 3;
		
		lambda1 = 0f;
		lambda2 = 0f;
		lambda3 = 0f;
		
		fase1 = 0;
		fase2 = 0;
		fase3 = 0;
		
		env1 = new Envoltoria();   
	    env2 = new Envoltoria();   
	    env3 = new Envoltoria();   
	  
	    uh1 = new UnidadeH();
	    uh2 = new UnidadeH();
	    uh3 = new UnidadeH();
	    
	    curva1 = new Curva(720);     
	    curva1.addPonto(  0f, 20f);
	    curva1.addPonto(20f, 2000);
	    curva1.addPonto(360f, 1500f);
	    curva1.addPonto(720f, 1500f);     
	
	    curva2 = new Curva(720);     
	    curva2.addPonto(0f, 30f);   
	    curva2.addPonto(50f, 1500f);
	    curva2.addPonto(200f, 500f);
	    curva2.addPonto(720f, 500f);     
	
	    curva3 = new Curva(720);     
	    curva3.addPonto(0f, 40f);   
	    curva3.addPonto(80f, 1000f);
	    curva3.addPonto(600f, 700f);
	    curva3.addPonto(720f, 700f);
	    
	    env1.setCURVA(curva1);      
	    env2.setCURVA(curva2);    
	    env3.setCURVA(curva3);
	    
	    uh1.setEnvoltoria(env1);
	    uh1.setH(h1);
	    uh1.setLambda(lambda1);
	    uh1.setFase(fase1);
	    uh1.setGanho(ganho1);

	    uh2.setEnvoltoria(env2);
	    uh2.setH(h2);
	    uh2.setLambda(lambda2);
	    uh2.setFase(fase2);
	    uh2.setGanho(ganho2);
	     
	    uh3.setEnvoltoria(env3);
	    uh3.setH(h3);
	    uh3.setLambda(lambda3);
	    uh3.setFase(fase3);
	    uh3.setGanho(ganho3);

	    ins = new InstrumentoAditivo();

	    ins.addUnidade(uh1);
	    ins.addUnidade(uh2);
	    ins.addUnidade(uh3);
	    ins.setGanho(ganhoTotal);
	    
	    return ins;
	}

	public static Dispositivo instrumento6(){
		Curva curva1, curva2, curva3;
		float ganho1, ganho2, ganho3, ganhoTotal;
		   float h1, h2, h3;
		   float lambda1, lambda2, lambda3;
		   float fase1, fase2, fase3;
		   
		   Envoltoria   env1, env2, env3;
		   
		   InstrumentoAditivo ins;

		   UnidadeH uh1, uh2, uh3;
			
		ganho1 = 10;
		ganho2 = 5;
		ganho3 = 3;
		ganhoTotal = 4;
		
		h1 = 1;
		h2 = 5;
		h3 = 9;
		
		lambda1 = 0f;
		lambda2 = 0f;
		lambda3 = 0f;
		
		fase1 = 0;
		fase2 = 0;
		fase3 = 0;
		
		env1 = new Envoltoria();   
	    env2 = new Envoltoria();   
	    env3 = new Envoltoria();   
	  
	    uh1 = new UnidadeH();
	    uh2 = new UnidadeH();
	    uh3 = new UnidadeH();
	    
	    curva1 = new Curva(720);     
	    curva1.addPonto(  0f, 20f);
	    curva1.addPonto(20f, 2000);
	    curva1.addPonto(360f, 1500f);
	    curva1.addPonto(720f, 1500f);     
	
	    curva2 = new Curva(720);     
	    curva2.addPonto(0f, 30f);   
	    curva2.addPonto(50f, 1500f);
	    curva2.addPonto(200f, 500f);
	    curva2.addPonto(720f, 500f);     
	
	    curva3 = new Curva(720);     
	    curva3.addPonto(0f, 40f);   
	    curva3.addPonto(80f, 1000f);
	    curva3.addPonto(600f, 700f);
	    curva3.addPonto(720f, 700f);
	    
	    env1.setCURVA(curva1);      
	    env2.setCURVA(curva2);    
	    env3.setCURVA(curva3);
	    
	    uh1.setEnvoltoria(env1);
	    uh1.setH(h1);
	    uh1.setLambda(lambda1);
	    uh1.setFase(fase1);
	    uh1.setGanho(ganho1);

	    uh2.setEnvoltoria(env2);
	    uh2.setH(h2);
	    uh2.setLambda(lambda2);
	    uh2.setFase(fase2);
	    uh2.setGanho(ganho2);
	     
	    uh3.setEnvoltoria(env3);
	    uh3.setH(h3);
	    uh3.setLambda(lambda3);
	    uh3.setFase(fase3);
	    uh3.setGanho(ganho3);

	    ins = new InstrumentoAditivo();

	    ins.addUnidade(uh1);
	    ins.addUnidade(uh2);
	    ins.addUnidade(uh3);
	    ins.setGanho(ganhoTotal);
	    
	    return ins;
	}

	public static Dispositivo instrumento7(){
		Curva curva1, curva2, curva3;
		float ganho1, ganho2, ganho3, ganhoTotal;
		   float h1, h2, h3;
		   float lambda1, lambda2, lambda3;
		   float fase1, fase2, fase3;
		   
		   Envoltoria   env1, env2, env3;
		   
		   InstrumentoAditivo ins;

		   UnidadeH uh1, uh2, uh3;
			
		ganho1 = 4;
		ganho2 = 5;
		ganho3 = 8;
		ganhoTotal = 4;
		
		h1 = 1;
		h2 = 5;
		h3 = 9;
		
		lambda1 = 0f;
		lambda2 = 0f;
		lambda3 = 0f;
		
		fase1 = 0;
		fase2 = 0;
		fase3 = 0;
		
		env1 = new Envoltoria();   
	    env2 = new Envoltoria();   
	    env3 = new Envoltoria();   
	  
	    uh1 = new UnidadeH();
	    uh2 = new UnidadeH();
	    uh3 = new UnidadeH();
	    
	    curva1 = new Curva(720);     
	    curva1.addPonto(  0f, 20f);
	    curva1.addPonto(20f, 2000);
	    curva1.addPonto(360f, 1500f);
	    curva1.addPonto(720f, 1500f);     
	
	    curva2 = new Curva(720);     
	    curva2.addPonto(0f, 30f);   
	    curva2.addPonto(50f, 1500f);
	    curva2.addPonto(200f, 500f);
	    curva2.addPonto(720f, 500f);     
	
	    curva3 = new Curva(720);     
	    curva3.addPonto(0f, 40f);   
	    curva3.addPonto(80f, 1000f);
	    curva3.addPonto(600f, 700f);
	    curva3.addPonto(720f, 700f);
	    
	    env1.setCURVA(curva1);      
	    env2.setCURVA(curva2);    
	    env3.setCURVA(curva3);
	    
	    uh1.setEnvoltoria(env1);
	    uh1.setH(h1);
	    uh1.setLambda(lambda1);
	    uh1.setFase(fase1);
	    uh1.setGanho(ganho1);

	    uh2.setEnvoltoria(env2);
	    uh2.setH(h2);
	    uh2.setLambda(lambda2);
	    uh2.setFase(fase2);
	    uh2.setGanho(ganho2);
	     
	    uh3.setEnvoltoria(env3);
	    uh3.setH(h3);
	    uh3.setLambda(lambda3);
	    uh3.setFase(fase3);
	    uh3.setGanho(ganho3);

	    ins = new InstrumentoAditivo();

	    ins.addUnidade(uh1);
	    ins.addUnidade(uh2);
	    ins.addUnidade(uh3);
	    ins.setGanho(ganhoTotal);
	    
	    return ins;
	}

	public static Dispositivo instrumento8(){
		Curva curva1, curva2, curva3;
		float ganho1, ganho2, ganho3, ganhoTotal;
		   float h1, h2, h3;
		   float lambda1, lambda2, lambda3;
		   float fase1, fase2, fase3;
		   
		   Envoltoria   env1, env2, env3;
		   
		   InstrumentoAditivo ins;

		   UnidadeH uh1, uh2, uh3;
			
		ganho1 = 3;
		ganho2 = 5;
		ganho3 = 8;
		ganhoTotal = 4;
		
		h1 = 1;
		h2 = 3;
		h3 = 4;
		
		lambda1 = 0f;
		lambda2 = 0f;
		lambda3 = 0f;
		
		fase1 = 0;
		fase2 = 0;
		fase3 = 0;
		
		env1 = new Envoltoria();   
	    env2 = new Envoltoria();   
	    env3 = new Envoltoria();   
	  
	    uh1 = new UnidadeH();
	    uh2 = new UnidadeH();
	    uh3 = new UnidadeH();
	    
	    curva1 = new Curva(720);     
	    curva1.addPonto(  0f, 20f);
	    curva1.addPonto(20f, 2000);
	    curva1.addPonto(360f, 1500f);
	    curva1.addPonto(720f, 1500f);     
	
	    curva2 = new Curva(720);     
	    curva2.addPonto(0f, 30f);   
	    curva2.addPonto(50f, 1500f);
	    curva2.addPonto(200f, 500f);
	    curva2.addPonto(720f, 500f);     
	
	    curva3 = new Curva(720);     
	    curva3.addPonto(0f, 40f);   
	    curva3.addPonto(80f, 1000f);
	    curva3.addPonto(600f, 700f);
	    curva3.addPonto(720f, 700f);
	    
	    env1.setCURVA(curva1);      
	    env2.setCURVA(curva2);    
	    env3.setCURVA(curva3);
	    
	    uh1.setEnvoltoria(env1);
	    uh1.setH(h1);
	    uh1.setLambda(lambda1);
	    uh1.setFase(fase1);
	    uh1.setGanho(ganho1);

	    uh2.setEnvoltoria(env2);
	    uh2.setH(h2);
	    uh2.setLambda(lambda2);
	    uh2.setFase(fase2);
	    uh2.setGanho(ganho2);
	     
	    uh3.setEnvoltoria(env3);
	    uh3.setH(h3);
	    uh3.setLambda(lambda3);
	    uh3.setFase(fase3);
	    uh3.setGanho(ganho3);

	    ins = new InstrumentoAditivo();

	    ins.addUnidade(uh1);
	    ins.addUnidade(uh2);
	    ins.addUnidade(uh3);
	    ins.setGanho(ganhoTotal);
	    
	    return ins;
	}
	
}