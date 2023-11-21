package com.example.mvvm_pokemoncombat;

public class Pokemon {
    private String nombre;
    private int hp;
    private int ataque;
    private int defensa;
    private int ataqueEspecial;
    private int defensaEspecial;

    public Pokemon(String nombre, int hp, int ataque) {
        this.nombre = nombre;
        this.hp = hp;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
    }

    // Getters y setters para cada propiedad
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public void setAtaqueEspecial(int ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }

    public int getDefensaEspecial() {
        return defensaEspecial;
    }

    public void setDefensaEspecial(int defensaEspecial) {
        this.defensaEspecial = defensaEspecial;
    }

    // Método para calcular el daño recibido
    public void recibirDaño(int ataque, int ataqueEspecial) {
        // Fórmula simplificada para calcular el daño
        int daño = (ataque - this.defensa) + (ataqueEspecial - this.defensaEspecial);
        daño = Math.max(daño, 0); // Aseguramos que el daño no sea negativo
        this.hp -= daño;
        this.hp = Math.max(this.hp, 0); // Aseguramos que los HP no sean negativos
    }
}
