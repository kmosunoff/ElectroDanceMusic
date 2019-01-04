package sample;

public class User {
    private Integer id;
    private String nickname;

    public User(Integer id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public Integer getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }
}
