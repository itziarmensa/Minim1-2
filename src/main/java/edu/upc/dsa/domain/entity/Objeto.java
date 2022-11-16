package edu.upc.dsa.domain.entity;

import edu.upc.dsa.domain.entity.VO.RandomId;

public class Objeto {
    String objetoNombre;
    String objetoDescripcion;
    double price;
    String objectId;

    public Objeto(){
        this.objectId = RandomId.getId();
    }

    public Objeto(String objetoNombre, String objetoDescripcion, double price) {
        this.objetoNombre = objetoNombre;
        this.objetoDescripcion = objetoDescripcion;
        this.price = price;
    }

    public String getObjetoNombre() {
        return objetoNombre;
    }

    public void setObjetoNombre(String objetoNombre) {
        this.objetoNombre = objetoNombre;
    }

    public String getObjetoDescripcion() {
        return objetoDescripcion;
    }

    public void setObjetoDescripcion(String objetoDescripcion) {
        this.objetoDescripcion = objetoDescripcion;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
