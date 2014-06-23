package test.java.org.n52.hibernate;

import java.io.Serializable;
import java.sql.Array;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import main.java.org.n52.sos.config.rasdaman.HibernateMArrayType;
import rasj.RasGMArray;

@Entity
@Table (name = "Rastest")
public class RastestNoArray implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int id;

    @Column(name = "coll")
    private Double coll;


    public RastestNoArray() {

    }

    public RastestNoArray(int id, Double coll) {
        super();
        this.id = id;
        this.coll = coll;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Double getColl() {
        return coll;
    }

    public void setColl(Double coll) {
        this.coll = coll;
    }
}
