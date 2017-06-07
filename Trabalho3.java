import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileFilter;

public class Trabalho1 extends JFrame implements Runnable
{
    public void abra(){
            File arquivoMidi;
            JFileChooser escolha = new JFileChooser(".");
            FileFilter filtro = new FileFilter() {

                    @Override
                    public boolean accept(File f) {
                            if(!f.isFile())
                                    return true;
                            String nome = f.getName().toLowerCase();
                            if(nome.endsWith(".mid"))
                                    return true;
                            if(nome.endsWith(".midi"))
                                    return true;
                            return false;
                    }

                    @Override
                    public String getDescription() {
                            return "Arquivos Midi (*.mid, *,midi)";
                    }

            };

            escolha.setFileSelectionMode(JFileChooser.FILES_ONLY);
            escolha.setFileFilter(filtro);
            escolha.showOpenDialog(escolha);

            arquivoMidi = escolha.getSelectedFile();

            try{
                    if(sequenciador != null && sequenciador.isRunning()){
                            sequenciador.stop();
                            sequenciador.close();
                            sequenciador = null;
                    }

                    sequencia = MidiSystem.getSequence(arquivoMidi);
                    getDados(0);
                    sequenciador = MidiSystem.getSequencer();
                    sequenciador.setSequence(sequencia);
                    sequenciador.open();

            }catch (Throwable e1) {
                System.out.println("Erro em carregaArquivoMidi: "+ e1.toString());
            }
        }
}
