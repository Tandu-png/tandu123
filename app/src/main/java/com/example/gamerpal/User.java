package com.example.gamerpal;

import java.util.List;

public class User {
    private String uid;
    private String email;
    private String displayName;
    private String username;
    private List<String> favoriteGames; // Có thể null nếu chưa chọn

    // Bắt buộc: Constructor rỗng để Firebase đọc dữ liệu
    public User() {}

    // Constructor đầy đủ
    public User(String email, String displayName, String username) {
        this.email = email;
        this.displayName = displayName;
        this.username = username;
    }

    // Getter và Setter
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getFavoriteGames() {
        return favoriteGames;
    }

    public void setFavoriteGames(List<String> favoriteGames) {
        this.favoriteGames = favoriteGames;
    }
}
