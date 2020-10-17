package chatapplication_server.components;

public class KeyStorage {
    private int ownSecret;
    private int sharedSecret;
    public KeyStorage (int ownSecret){
        this.ownSecret = ownSecret;
    }

    public int getSharedSecret() {
        return this.sharedSecret;
    }

    public int getOwnSecret() {
        return this.ownSecret;
    }

    public int setSharedSecret(int sharedSecret) {
         return this.sharedSecret = sharedSecret;
    }

    public int calcSecret(int receivedKey) {
        this.sharedSecret = (int) (Math.pow(receivedKey, this.ownSecret) % SharedKeys.p);
        return this.sharedSecret;
   }

}
