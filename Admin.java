package adminclass;
public class Admin {
    public int uid;
    public int pass;
    String name;
    int code, qty;
    double price;

    public Admin(int uid, int pass) {
        this.uid = uid;
        this.pass = pass;
    }

    public void inven_updat(String name, int code, double price, int qty) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.qty = qty;
    }
}
