import sintese.*;
public class musica{
	public static void main(String[] args){
		Melodia melodiaPronta = new Melodia();
		Nota notaPausa;
		Nota novaNota;
		novaNota = new Nota(1.25, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.8916667, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.35833335, 1760.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.53541666, 440.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.35625, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.35833335, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.53541666, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.35625, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.35833335, 1760.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17708333, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.35833335, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.35625, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.35833335, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17708333, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.35833335, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.35625, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17916667, 1760.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17916667, 1760.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17708333, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.35833335, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.35625, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17916667, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17916667, 3520.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17708333, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.35833335, 440.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17708333, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17916667, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17916667, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17916667, 1760.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17708333, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17916667, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17916667, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17708333, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17916667, 440.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17916667, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17916667, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17708333, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17916667, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17916667, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17708333, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17916667, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		novaNota = new Nota(0.17916667, 880.0, 80.0);
		melodiaPronta.addNota(novaNota);
		InstrumentoAditivo ins = (InstrumentoAditivo)instrumentos.instrumento1();
		Som som1 = melodiaPronta.getSom(ins);
		som1.setNome("instrumento1");
		som1.salvawave();
		som1.visualiza();
	}
}
