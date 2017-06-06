public void abreBanco()
{
	//Escolha do Arquivo SoundFont desejado
	File arquivoSF2;

	JFileChooser escolha = new JFileChooser(".");
		FileFilter filtro = new FileFilter() {

			@Override
			public boolean accept(File f) {
				if(!f.isFile())
					return true;
				String nome = f.getName().toLowerCase();
				if(nome.endsWith(".sf2"))
					return true;
				if(nome.endsWith(".sfz"))
					return true;
				return false;
			}

			@Override
			public String getDescription() {
				return "Arquivos Soundfont (*.sf2, *,.sfz)";
			}
			
		};

	escolha.setFileSelectionMode(JFileChooser.FILES_ONLY);
	escolha.setFileFilter(filtro);
	escolha.showOpenDialog(escolha);

	arquivoSF2 = escolha.getSelectedFile();


	//Tentativa de substituir pela nova soundfont
	try 
	{ 
		sintetizador = MidiSystem.getSynthesizer();
        sintetizador.open();
  	}
  	catch (Exception ex) 
  	{ 
  		System.out.println("Erro em MidiSystem.getSynthesizer(): " + ex);                                  
		return; 
	}
                    
	Soundbank bancodefault = sintetizador.getDefaultSoundbank();
	if(bancodefault != null)
	{
		sintetizador.unloadAllInstruments(bancodefault);          
	}         

	try 
	{ 
		bancoSELECIONADO = MidiSystem.getSoundbank(arquivoSF2); 
	}
	catch (Exception e) 
	{ 
		e.printStackTrace(); 
	}

	sintetizador.loadAllInstruments( bancoSELECIONADO);
	}

	public void getMidi()
	            Track[] trilhas = sequencia.getTracks();
             	Informacoes = new ArrayList<String>();
              for(int i=0; i<trilhas.length; i++)
              {
                Informacoes.add("Início da trilha nº " + i + " **********************");
	             System.out.println("------------------------------------------");
                Track trilha =  trilhas[i];
                


                for(int j=0; j<trilha.size(); j++)
                {
                  Informacoes.add("Trilha nº " + i );
                  Informacoes.add("Evento nº " + j);
                  MidiEvent   e          = trilha.get(j);
                  MidiMessage mensagem   = e.getMessage();
                  long        tique      = e.getTick();
                  
                  int n = mensagem.getStatus();
                  
                  String nomecomando = ""+n;
                  
                  switch(n)
                  {
                      case 128: nomecomando = "noteON"; break;
                      case 144: nomecomando = "noteOFF"; break;
                      case 255: nomecomando = "MetaMensagem  (a ser decodificada)"; break; 
                      //---(introduzir outros casos)
                  }
                  
                  Informacoes.add("       Mensagem: " + nomecomando );
                  Informacoes.add("       Instante: " + tique );
	              Informacoes.add("------------------------------------------");                                    
                }
              }

	  }