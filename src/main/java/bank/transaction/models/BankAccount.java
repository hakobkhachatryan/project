package bank.transaction.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class BankAccount {
    private int id;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 2,max = 30,message = "2->30")
    private String name;
    @NotEmpty(message = "name should not be empty")
    @Email(message = "should be valid")
    private String email;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 2,max = 20,message = "2->20")
    private String password;

    public BankAccount() {
    }

    public BankAccount(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
