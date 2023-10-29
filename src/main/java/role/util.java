package role;

public class util {
    private static util instance;
    private String role;

    private util() {
        // Private konstruktor untuk mencegah instansiasi langsung.
    }

    public static util getInstance() {
        if (instance == null) {
            instance = new util();
        }
        return instance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
