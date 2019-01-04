package sample;

public class Artist {
    private Integer id;
    private String nickname;

    public Artist(Integer id, String nickname) {
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
