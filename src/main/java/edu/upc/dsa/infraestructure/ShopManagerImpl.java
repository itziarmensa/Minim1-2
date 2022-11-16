package edu.upc.dsa.infraestructure;

import edu.upc.dsa.domain.ShopManager;
import edu.upc.dsa.domain.entity.Objeto;
import edu.upc.dsa.domain.entity.User;
import edu.upc.dsa.domain.entity.VO.Credentials;
import edu.upc.dsa.domain.entity.exceptions.*;

import org.apache.log4j.Logger;

import java.util.*;

import static org.apache.log4j.LogManager.getLogger;

public class ShopManagerImpl implements ShopManager {
    private static ShopManager instance;

    protected List<Objeto> objects;

    protected Map<String, User> users;

    final static Logger logger = getLogger(ShopManagerImpl.class);

    public ShopManagerImpl(){
        this.objects = new ArrayList<>();
        this.users = new HashMap<>();
    }

    public static ShopManager getInstance() {
        if (instance==null) instance = new ShopManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.users.size();
        logger.info("size " + ret);

        return ret;
    }

    public User userInfo(String userId){
        return this.users.get(userId);
    }

    public Objeto getObject(String objectId) {
        return this.objects.stream()
                .filter(x->objectId.equals(x.getObjectId()))
                .findFirst()
                .orElse(null);
    }

    public Objeto objectInfo(String objectId){
        return getObject(objectId);
    }

    public Boolean userExistsByCredentials(Credentials credentials) {
        for(User user : this.users.values()){
            if(user.hasEmail(credentials)){
                return true;
            }
        }
        return false;
    }

    public void registroUsuario(String nombre, String apellidos, String fecha, Credentials credenciales) throws UserAlreadyExistsException{
        logger.info("Trying register of user with information: ("+nombre+", "+apellidos+", "+fecha+", {credentials})");
        if(userExistsByCredentials(credenciales)){
            logger.warn("Register not possible, User already exists! :(");
            throw new UserAlreadyExistsException();
        }
        User user = new User(nombre, apellidos, fecha, credenciales);
        this.users.put(user.getUserId(), user);
        logger.info("Register of user: "+user+" was done!");

    }

    public List<User> orderUsersByAlph(){
        logger.info("User list was requested, proceeding to obtain it.");
        List<User> users = new ArrayList<>(this.users.values());
        users.sort((User u1, User u2)->{
            int value = u1.getUserSurname().compareToIgnoreCase(u2.getUserSurname());
            if (value==0){
                value = u1.getUserName().compareToIgnoreCase(u2.getUserName());
            }
            return value;
        });
        logger.info("User list was correctly found! :)");
        return users;
    }

    public User getUserByCredentials(Credentials credentials) {
        return this.users.values().stream()
                .filter(x->x.getCredentials().isEqual(credentials))
                .findFirst()
                .orElse(null);
    }
    public User loginUser(Credentials credenciales) throws UserCredentialsNotValidException{
        logger.info("Login of user with email "+credenciales.getEmail()+" was requested.");
        User user = getUserByCredentials(credenciales);
        if(user==null){
            logger.warn("User credentials are not valid");
            throw new UserCredentialsNotValidException();
        }
        logger.info("User login was correctly done with email ("+credenciales.getEmail()+")! :)");
        return user;
    }

    public void addObjeto(String nombre, String descripcion, double precio){
        logger.info("Proceeding to add object with information "+nombre+", "+descripcion+", "+precio+"");
        Objeto objeto = new Objeto(nombre,descripcion,precio);
        logger.info("Object was correctly added");
    }

    public List<Objeto> orderObjectsByPrice(){
        logger.info("Object list was requested.");
        this.objects.sort((Objeto o1, Objeto o2)->Double.compare(o2.getPrice(),o1.getPrice()));
        logger.info("Object list was correctly found :)");
        return this.objects;
    }

    public void comprarObjeto(String userId, String objectId) throws UserDoesNotExistException, ObjectDoesNotExistException, NotEnoughMoneyException{
        logger.info("Purchase of object with id: "+objectId+" was requested from user with id: "+userId+".");
        if(!this.users.containsKey(userId)){
            logger.warn("User with id: "+userId+" does not exist");
            throw new UserDoesNotExistException();
        }
        Objeto object;
        if((object = getObject(objectId)) == null) {
            logger.warn("Object with id: "+objectId+" does not exist");
            throw new ObjectDoesNotExistException();
        }
        this.users.get(userId).purchaseObject(object);
        logger.info("Purchase was correctly effectuated");
    }

    public List<Objeto> listadoObjetosComprados(String userId) throws UserDoesNotExistException{
        logger.info("Request of purchased objects form user with id: "+userId+".");
        User user = this.users.get(userId);
        if (user == null){
            logger.warn("User with id: "+userId+" does not exist");
            throw new UserDoesNotExistException();
        }
        logger.info("Request was correctly effectuated");
        return user.getObjetosComprados();
    }

    public int numUsers(){
        return users.size();
    }
    public int numObjects(){
        return objects.size();
    }

}
