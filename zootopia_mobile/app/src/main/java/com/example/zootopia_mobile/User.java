package com.example.zootopia_mobile;

public class User {
    private int id;
    private String codePostal;
    private String noTel;
    private String name;
    private String email;
    private String password;

    public User(int id, String codePostal, String noTel, String name, String email, String password) {
        this.id = id;
        this.codePostal = codePostal;
        this.noTel = noTel;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters
    public int getId() { return id; }
    public String getCodePostal() { return codePostal; }
    public String getNoTel() { return noTel; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    // Setters
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }
    public void setNoTel(String noTel) { this.noTel = noTel; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", codePostal='" + codePostal + '\'' +
                ", noTel='" + noTel + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
