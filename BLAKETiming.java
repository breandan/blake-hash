import java.io.*;

public class BLAKETiming {
	private static BLAKEHash bh = new BLAKEHash();
	
	public static void main(String[] args) {
	    long messageTotalLength = Integer.parseInt(args[0]);
	    byte [] len = null;
	    
	    for(int i = 0; i < messageTotalLength ; i++ ){
	    	bh.hash( 0);
	    }
	    
	    
	    try {
	    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    	DataOutputStream dos = new DataOutputStream(baos);
	    	dos.writeLong(messageTotalLength);
	    	dos.close();
	    	len = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    byte currentpad = (byte)128;
	    
	    while (messageTotalLength % 64 != 55){
	    	bh.hash((int)currentpad);
	    	currentpad = 0; 
	    	messageTotalLength ++;
	    }
	    
	    bh.hash((int)(byte)(currentpad ^ 1));
	    
	    for (int i = 0; i < 8; i++){
	    	bh.hash((int)len[i]);
	    }
	    byte[] BLAKEOutput = new byte[32];
	    bh.digest(BLAKEOutput);
	    System.out.print(new String(BLAKEOutput));

	}
	
}
