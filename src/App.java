import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        gerarArquivo(lerArquivo());
    }

    public static String lerArquivo() throws IOException{
        String retorno ="";
        Path caminho = Paths.get("src//Input.txt");
        List<String> lista = Files.readAllLines(caminho, StandardCharsets.UTF_8);
        for (String aula : lista) {
            retorno += aula.substring(aula.indexOf(": ")+2) + ";";
        }
        return retorno;
    }

    public static void gerarArquivo(String input) throws IOException{
        String[] entrada = input.split(";");
        FileWriter arq = new FileWriter("src//Resultado.html");
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.println("<!DOCTYPE html>");
        gravarArq.println("<html>");
        gravarArq.println("<head>");
        gravarArq.println("</head>");
        gravarArq.println("<body>");
        gravarArq.println("<svg height='2000' width='2000'>");

        Integer iniciox = 700;
        Integer inicioy = 300;
        Integer angulo = 0;
        Integer direcaox = 1;
        Integer direcaoy = -1;
        boolean horizontal = false;
        String caracter = entrada[0];
        String rule1 = entrada[1];
        String rule2 = entrada[2];
        Integer quantidade = Integer.parseInt(entrada[3]);
        char entrada1 = entrada[4].charAt(0);
        char entrada2 = entrada[5].charAt(0);
        String auxiliar = caracter;
 
        for (int i = 0; i < quantidade; i++) {
            caracter = auxiliar;
            auxiliar = "";
            for (int j = 0; j < caracter.length(); j++) {
                char d = caracter.charAt(j);
                if( d == entrada1)
                    auxiliar += rule1 ;
                else if(d == entrada2)
                    auxiliar += rule2;
                else
                    auxiliar += d;
            }
        }        

        for (int i = 0; i < auxiliar.length(); i++) {
            
            char c = auxiliar.charAt(i);
            if(c == 'F'){           
                if( horizontal == true){
                    gravarArq.println("<line x1='" + iniciox + "' y1=" + inicioy + " x2=" + (iniciox + (10 * direcaox ))  + " y2=" + inicioy + " style='stroke:rgb(0,0,0);stroke-width:2' />");
                    iniciox += (10 * direcaox);
                }
                else{
                    gravarArq.println("<line x1='" + iniciox + "' y1=" + inicioy + " x2=" + iniciox   + " y2=" + (inicioy + (10 * direcaoy)) + " style='stroke:rgb(0,0,0);stroke-width:2' />");
                    inicioy += (10 * direcaoy);
                }
            }
            else if( c == 'f'){
                if( horizontal == true)
                    iniciox += (10 * direcaox);
                else
                    inicioy += (10 * direcaoy);
            }
            else if(c == '-'){
                angulo -= 90;

                if(angulo < 0)
                    angulo = 270;

            }
            else if(c == '+'){
                angulo += 90;

                if(angulo == 360)
                angulo = 0;
            }

            if(angulo == 0 ){
                direcaox = 1;
                direcaoy = -1;
                horizontal = true;
            }
            else if(angulo == 90 ){
                direcaox = -1;
                direcaoy = -1;
                horizontal = false;

            }
            else if(angulo == 180 ){
                direcaox = -1;
                direcaoy = 1;
                horizontal = true;

            }
            else if(angulo == 270 ){
                direcaox = 1;
                direcaoy = 1;
                horizontal = false;

            }
        }
        gravarArq.println("</svg>");
        gravarArq.println("</body>");
        gravarArq.println("</html>");
        arq.close();
    }
}

