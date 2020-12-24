package bank.transaction.dao;


import bank.transaction.models.BankAccount;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class BankAccountDAO {
    private  final String URL = "jdbc:mysql://localhost:3306/bank.transaction";
    private  final String USERNAME = "root";
    private  final String PASSWORD = "";

    private  Connection connection;


    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public List<BankAccount> index () {
        List<BankAccount> bankAccounts = new ArrayList<>();
        try
        {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM user";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                BankAccount bankAccount = new BankAccount();

                bankAccount.setId(resultSet.getInt("id"));
                bankAccount.setName(resultSet.getString("name"));
                bankAccount.setEmail(resultSet.getString("email"));
                bankAccount.setPassword(resultSet.getString("password"));

                bankAccounts.add(bankAccount);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bankAccounts;
    }


    public BankAccount show(int id) {
        BankAccount bankAccount = null;
        try {

            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM user WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            bankAccount = new BankAccount();
            bankAccount.setId(resultSet.getInt("id"));
            bankAccount.setName(resultSet.getString("name"));
            bankAccount.setEmail(resultSet.getString("email"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bankAccount;
    }

    public void save(BankAccount bankAccount) {

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO user VALUES(10,?,?,?,user )");
            preparedStatement.setString(1, bankAccount.getName());
            preparedStatement.setString(2, bankAccount.getEmail());
            preparedStatement.setString(3, bankAccount.getPassword());

            preparedStatement.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(int id,BankAccount updateBankAccount){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE user SET name=?, email=?, password=? WHERE id=?");

            preparedStatement.setString(1, updateBankAccount.getName());
            preparedStatement.setString(2, updateBankAccount.getEmail());
            preparedStatement.setString(3, updateBankAccount.getPassword());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}
