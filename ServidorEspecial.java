import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorEspecial<total> extends Thread {
    private static ArrayList<BufferedWriter> clientes;
    private static ServerSocket server;
    private String nome;
    private Socket con;
    private InputStream in;
    private InputStreamReader inr;
    private BufferedReader bfr;

    public ServidorEspecial(Socket con){
        this.con = con;
        try {
            in  = con.getInputStream();
            inr = new InputStreamReader(in);
            bfr = new BufferedReader(inr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        int n1, n2, operacao;
        double total = 0.0;
        char simbolo;

        ServerSocket servidor = new ServerSocket (9999);
        System.out.println ("Porta Servidor Especialista Aberta");

        System.out.println ("Aguardando ...");
        Socket cliente = servidor.accept();

        System.out.println("nova conexao "+ cliente.getInetAddress().getHostAddress());

        ObjectOutputStream output = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());

        operacao = input.readInt();
        n1 = input.readInt();
        n2 = input.readInt();

        switch(operacao){
            case 1:
                simbolo = '%';
                total = (Double.parseDouble(String.valueOf(n1)) * Double.parseDouble(String.valueOf(n2)) / 100);
                break;
            case 2:
                simbolo = 'r';
                total = Math.sqrt(Double.parseDouble(String.valueOf(n1)));
                break;
            case 3:
                simbolo = '^';
                total = Math.pow(n1, n2);
                break;
            default:
                System.out.println("operacao invalida");
                simbolo = '@';
        }

        output.writeDouble(total);
        output.writeChar(simbolo);
        output.flush();
        servidor.close();

    }
}

