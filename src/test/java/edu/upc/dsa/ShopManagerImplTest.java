package edu.upc.dsa;

import edu.upc.dsa.domain.ShopManager;
import edu.upc.dsa.domain.entity.VO.Credentials;
import edu.upc.dsa.domain.entity.VO.EmailAddress;
import edu.upc.dsa.domain.entity.exceptions.EmailAddressNotValidException;
import edu.upc.dsa.domain.entity.exceptions.UserAlreadyExistsException;
import edu.upc.dsa.infraestructure.ShopManagerImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShopManagerImplTest {
    ShopManager shopManager;

    @Before
    public void setUp() throws EmailAddressNotValidException, UserAlreadyExistsException{
        this.shopManager = new ShopManagerImpl();

        Credentials credentials1 = new Credentials(new EmailAddress("albaromagomez@gmail.com"), "Test123");
        this.shopManager.registroUsuario("Alba", "Roma Gómez", "23/11/2001", credentials1);
        Credentials credentials2 = new Credentials(new EmailAddress("susanagr@gmail.com"), "123test");
        this.shopManager.registroUsuario("Susana", "Roma Gómez", "19/5/1971", credentials2);
        Credentials credentials3 = new Credentials(new EmailAddress("oriolplansponsa@gmail.com"), "123456");
        this.shopManager.registroUsuario("Oriol", "Plans Ponsa", "11/4/1997", credentials3);

        this.shopManager.addObjeto("Pa Bimbo", "un pa molt bo", 2.3);
        this.shopManager.addObjeto("Talla ungles", "talla ungles per quan les tens llargues", 6.1);
        this.shopManager.addObjeto("Cocacola", "es molt bona", 1.2);
        this.shopManager.addObjeto("Llibreta", "per escriure algo", 4.9);
        this.shopManager.addObjeto("Anell", "per posar en el dit", 11.1);

    }

    @After
    public void tearDown(){
        this.shopManager = null;
    }

    @Test
    public void testRegistro() throws EmailAddressNotValidException, UserAlreadyExistsException{
        Assert.assertEquals(3,this.shopManager.numUsers());
        Assert.assertEquals(5,this.shopManager.numObjects());

        Credentials credentials = new Credentials(new EmailAddress("blancaromagomez@gmail.com"), "Test123");
        this.shopManager.registroUsuario("Blanca", "Roma Gómez", "23/11/2000", credentials);

        Assert.assertEquals(4, this.shopManager.numUsers());
    }

}
