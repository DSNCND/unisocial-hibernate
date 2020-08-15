package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario extends EntidadPersistente {
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "fechaDeNacimiento", columnDefinition = "DATE")
    private LocalDate fechaDeNacimiento;

    @Column(name = "telefono")
    private int telefono;

    @Column(name = "legajo")
    private int legajo;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "reputacion_id")
    private Reputacion reputacion;

    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.ALL})
    private List<Aporte> aportes;

    @ManyToOne
    private Rol rol;

    public Usuario(){
        this.aportes = new ArrayList<>();
        this.reputacion = new BuenaReputacion();
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Rol getRol() {
        return rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public Reputacion getReputacion() {
        return reputacion;
    }

    public void setReputacion(Reputacion reputacion) {
        this.reputacion = reputacion;
    }

    public List<Aporte> getAportes() {
        return aportes;
    }

    public void puntuar(Aporte unAporte, int puntos){
        Puntuacion puntuacion = new Puntuacion(puntos, this);
        puntuacion.setAporte(unAporte);
    }

    public void recibirPuntuacionDeAporte(Puntuacion puntuacion){
        this.reputacion.recibirPuntuacionDeAporte(puntuacion, this);
    }
}
