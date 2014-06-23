package test.java.org.n52.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "Rastest")
public class RastestConst implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "coll")
    private Double coll;
    

    public RastestConst() {

    }
    
    public RastestConst(Double coll) {
        super();
        this.coll = coll;
    }
    
    public Double getColl() {
        return coll;
    }
    
    public void setColl(Double coll) {
        this.coll = coll;
    }
}
