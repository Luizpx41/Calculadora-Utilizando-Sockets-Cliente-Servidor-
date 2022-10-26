import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException {
        int operacao;
        double n1, n2, total = 0.0;
        char simbolo;

        ServerSocket servidor = new ServerSocket (9998);
        System.out.println ("Porta Servidor Aberta");

        System.out.println ("Aguardando ...");
        Socket cliente = servidor.accept();

        System.out.println("nova conexao "+ cliente.getInetAddress().getHostAddress());

        ObjectOutputStream output = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());

        operacao = input.readInt();
        n1 = input.readDouble();
        n2 = input.readDouble();

        switch(operacao){
            case 1:
                simbolo = '+';
                total = n1 + n2;
                break;
            case 2:
                simbolo = '-';
                total = n1 - n2;
                break;
            case 3:
                simbolo = '*';
                total = n1 * n2;
                break;
            case 4:
                simbolo = ':';
                total = n1 / n2;
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



