package edu.upc.dsa.domain.entity;

import edu.upc.dsa.domain.entity.VO.Credentials;
import edu.upc.dsa.domain.entity.VO.RandomId;
import edu.upc.dsa.domain.entity.exceptions.NotEnoughMoneyException;

import java.util.LinkedList;
import java.util.List;

public class User {
    String userName;
    String userSurname;
    String userBirth;
    Credentials credentials;
    String userId;
    List<Objeto> objetosComprados;
    int dsaCoins;

    public User(){
        this.userId = RandomId.getId();
        this.objetosComprados = new LinkedList<>();
        this.dsaCoins = 50;
    }

    public User(String userName, String userSurname, String userBirth, Credentials credentials) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.userBirth = userBirth;
        this.credentials = credentials;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Objeto> getObjetosComprados() {
        return objetosComprados;
    }

    public void setObjetosComprados(List<Objeto> objetosComprados) {
        this.objetosComprados = objetosComprados;
    }

    public int getDsaCoins() {
        return dsaCoins;
    }

    public void setDsaCoins(int dsaCoins) {
        this.dsaCoins = dsaCoins;
    }

    public void purchaseObject(Objeto object) throws NotEnoughMoneyException {
        if(object.getPrice()>this.dsaCoins){
            throw new NotEnoughMoneyException();
        }

        this.dsaCoins = this.dsaCoins - object.getPrice();
        objetosComprados.add(object);
    }

    public Boolean hasEmail(Credentials credentials) {
        return this.credentials.getEmail().isEqual(credentials.getEmail());
    }
}
