import java.net.*;

public class ServerMain{
    public static main void(String[] args){
        int i = 1; // 클라이언트를 구별하기 위한 변수.
        try{
            ServerSocket s = new ServerSocket(8199);
                for(;;i++;){
                    Socket incoming = s.accept(); // 클라이언트와 연결된 소켓.
                    System.out.println("Spawning" + i);
                    new ClientHandler(incoming,i).start();
                }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
