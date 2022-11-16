package edu.upc.dsa.domain;

import edu.upc.dsa.domain.entity.Objeto;
import edu.upc.dsa.domain.entity.User;
import edu.upc.dsa.domain.entity.VO.Credentials;
import edu.upc.dsa.domain.entity.exceptions.*;

import java.util.List;

public interface ShopManager {
    public int size();
    public User userInfo(String userId);
    public Objeto objectInfo(String objectId);
    public void registroUsuario(String nombre, String apellidos, String fecha, Credentials credenciales) throws UserAlreadyExistsException;
    public List<User> orderUsersByAlph();
    public User loginUser(Credentials credenciales) throws UserCredentialsNotValidException;
    public void addObjeto(String nombre, String descripcion, double precio);
    public List<Objeto> orderObjectsByPrice();
    public void comprarObjeto(String userId, String objectId) throws UserDoesNotExistException, ObjectDoesNotExistException, NotEnoughMoneyException;
    public List<Objeto> listadoObjetosComprados(String userId) throws UserDoesNotExistException;
    public int numUsers();
    public int numObjects();
}
