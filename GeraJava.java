//Packages de Som
import sintese.*;
import sintese.Melodia;
import javax.sound.midi.*;

//Packages de I/O
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.math.*;

public class GeraJava{
	PrintWriter writer;
	private int numMusica = 1;
	public GeraJava(int numero){
		numMusica = numero;
		try{
			String nome = "musica" + numMusica + ".java";
		    writer = new PrintWriter(nome, "UTF-8");
		    writer.println("import sintese.*;");
		    writer.println("public class musica"
		    		+ numMusica + "{");
		    writer.println("\tpublic static void main(String[] args){");
		    writer.println("\t\tMelodia melodiaPronta = new Melodia();");
		    writer.println("\t\tNota notaPausa;");
		    writer.println("\t\tNota novaNota;");
		    
		} catch (IOException e) {
			
		}
		
	}
	
	public void criaNota(float duracao){
		writer.println("\t\tnotaPausa = new Nota(" + duracao + ");");
		writer.println("\t\tmelodiaPronta.addNota(notaPausa);");
	}
	
	public void criaNota(float duracaoNota, float frequencia, float intensidade){
		writer.println("\t\tnovaNota = new Nota(" + Math.abs(duracaoNota)/1000 + ", "
				+ Math.abs(frequencia) + ", " + Math.abs(intensidade) + ");");
		writer.println("\t\tmelodiaPronta.addNota(novaNota);");
	}
	
	public void defineInstrumento(int instrumento){
		instrumento += numMusica%8;
		writer.println("\t\tInstrumentoAditivo ins = (InstrumentoAditivo)instrumentos.instrumento"
				+ instrumento + "();");
	}
	
	public void fechaJava(){
		writer.println("\t\tSom som1 = melodiaPronta.getSom(ins);");
		writer.println("\t\tsom1.setNome(\"instrumento1\");");
		writer.println("\t\tsom1.salvawave();");
		writer.println("\t\tsom1.visualiza();");
		writer.println("\t\tsom1.salvawave();");
		writer.println("\t}");
		writer.println("}");
		writer.close();
	}
}