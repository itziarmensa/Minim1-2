package edu.upc.dsa.infraestructure;

import edu.upc.dsa.domain.ShopManager;
import edu.upc.dsa.domain.entity.Objeto;
import edu.upc.dsa.domain.entity.User;
import edu.upc.dsa.domain.entity.VO.Credentials;
import edu.upc.dsa.domain.entity.exceptions.UserAlreadyExistsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ShopManagerImpl implements ShopManager {
    private static ShopManager instance;

    protected List<Objeto> objects;

    protected Map<String, User> users;

    final static Logger logger = Logger.getLogger(ShopManagerImpl.class);

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
            logger.warning("Register not possible, User already exists! :(");
        }
    }
}
