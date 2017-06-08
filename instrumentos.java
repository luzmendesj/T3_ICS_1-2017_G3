import sintese.*;
import javax.sound.midi.*;

class instrumentos{
		float ganho1, ganho2, ganho3, ganhoTotal;
	   float h1, h2, h3;
	   float lambda1, lambda2, lambda3;
	   float fase1, fase2, fase3;
	   
	   Envoltoria   env1, env2, env3;
	   
	   InstrumentoAditivo ins;
	   
	public static Dispositivo instrumento1(){
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

	public static Dispositivo instrumento2(){
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

	public static Dispositivo instrumento3(){
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