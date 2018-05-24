/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author sniffsnirr
 */
@Entity 
@Table(name="ACTOR")
public class Actor implements Serializable{
    private int Id;
    private String Fio;
    private String Pic;
    private Set<Movie> movies=new HashSet<Movie>();
   

    public Actor(int Id,String Fio, String Pic) {
        this.Id=Id;
        this.Fio = Fio;
        this.Pic = Pic;
    }

    public Actor() {
    }

    
    public void setId(int Id) {
        this.Id = Id;
    }
@Id
 //@GeneratedValue(strategy=GenerationType.SEQUENCE)
//@GeneratedValue(strategy=GenerationType.IDENTITY)
//@GeneratedValue(strategy=GenerationType.AUTO)
//@GeneratedValue(strategy=GenerationType.TABLE)
@Column(name="ID")
  public int getId() {
        return Id;
    }

   @Column(name="FIO")
    public String getFio() {
        return Fio;
    }

    public void setFio(String Fio) {
        this.Fio = Fio;
    }
@Column(name="PIC")
    public String getPic() {
        return Pic;
    }

    public void setPic(String Pic) {
        this.Pic = Pic;
    }
    
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "actors")
   // @ManyToMany
   //@JoinTable(name="MOVIE_ACTOR", joinColumns=@JoinColumn(name="ACTOR_ID"), inverseJoinColumns=@JoinColumn(name="MOVIE_ID"))
    public  Set<Movie> getMovies()
    {
        return this.movies;
    }
    
    public void setMovies(Set<Movie> movies)
    {
        this.movies=movies;
    }
    
    
    public String toString(){
    return this.getFio();
    }
    
     public String toCSV()
     {
     return  Fio.replaceAll(";", ".")+";"+Pic.replaceAll(";", ".")+";";
     }
    
}
