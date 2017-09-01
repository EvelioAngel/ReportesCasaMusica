/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.reportes.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eve
 */
@Entity
@Table(name = "artista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artista.findAll", query = "SELECT a FROM Artista a")
    , @NamedQuery(name = "Artista.findByIdArtista", query = "SELECT a FROM Artista a WHERE a.idArtista = :idArtista")
    , @NamedQuery(name = "Artista.findByCodigo", query = "SELECT a FROM Artista a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "Artista.findByNombre", query = "SELECT a FROM Artista a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Artista.findByPrimerApellido", query = "SELECT a FROM Artista a WHERE a.primerApellido = :primerApellido")
    , @NamedQuery(name = "Artista.findBySegundoApellido", query = "SELECT a FROM Artista a WHERE a.segundoApellido = :segundoApellido")
    , @NamedQuery(name = "Artista.findByCi", query = "SELECT a FROM Artista a WHERE a.ci = :ci")
    , @NamedQuery(name = "Artista.findByDireccion", query = "SELECT a FROM Artista a WHERE a.direccion = :direccion")
    , @NamedQuery(name = "Artista.findByNombrecompleto", query = "SELECT a FROM Artista a WHERE a.nombrecompleto = :nombrecompleto")
    , @NamedQuery(name = "Artista.findByActivo", query = "SELECT a FROM Artista a WHERE a.activo = :activo")
    , @NamedQuery(name = "Artista.findByExonerado", query = "SELECT a FROM Artista a WHERE a.exonerado = :exonerado")
    , @NamedQuery(name = "Artista.findByFinexoneracion", query = "SELECT a FROM Artista a WHERE a.finexoneracion = :finexoneracion")})
public class Artista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_artista")
    private Integer idArtista;
    @Column(name = "codigo")
    private Integer codigo;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Size(max = 255)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Size(max = 11)
    @Column(name = "ci")
    private String ci;
    @Size(max = 255)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 255)
    @Column(name = "nombrecompleto")
    private String nombrecompleto;
    @Column(name = "activo")
    private Boolean activo;
    @Column(name = "exonerado")
    private Boolean exonerado;
    @Column(name = "finexoneracion")
    @Temporal(TemporalType.DATE)
    private Date finexoneracion;

    public Artista() {
    }

    public Artista(Integer idArtista) {
        this.idArtista = idArtista;
    }

    public Integer getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(Integer idArtista) {
        this.idArtista = idArtista;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getExonerado() {
        return exonerado;
    }

    public void setExonerado(Boolean exonerado) {
        this.exonerado = exonerado;
    }

    public Date getFinexoneracion() {
        return finexoneracion;
    }

    public void setFinexoneracion(Date finexoneracion) {
        this.finexoneracion = finexoneracion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArtista != null ? idArtista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artista)) {
            return false;
        }
        Artista other = (Artista) object;
        if ((this.idArtista == null && other.idArtista != null) || (this.idArtista != null && !this.idArtista.equals(other.idArtista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.reportes.models.Artista[ idArtista=" + idArtista + " ]";
    }
    
}
