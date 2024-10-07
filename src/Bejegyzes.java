import java.time.LocalDateTime;

public class Bejegyzes {
    private String szerzo;
    private String tartalom;
    private Integer likeok;
    private LocalDateTime letrejott;
    private LocalDateTime szerkesztve;

    Bejegyzes(String szerzo, String tartalom) {
        LocalDateTime ldt = LocalDateTime.now();
        this.szerzo = szerzo;
        this.tartalom = tartalom;
        this.likeok = 0;
        this.letrejott = ldt;
        this.szerkesztve = ldt;
    }

    public String getSzerzo() {
        return szerzo;
    }

    public String getTartalom() {
        return tartalom;
    }

    public void setTartalom(String tartalom) {
        this.tartalom = tartalom;
        this.szerkesztve = LocalDateTime.now();
    }

    public Integer getLikeok() {
        return likeok;
    }

    public LocalDateTime getLetrejott() {
        return letrejott;
    }

    public LocalDateTime getSzerkesztve() {
        return szerkesztve;
    }

    public void like(){
        this.likeok++;
    }

    @Override
    public String toString() {
        String serkesztveText = "";
        if(!this.szerkesztve.equals(this.letrejott)){
            serkesztveText = "Szerkesztve: " + this.szerkesztve + "\n";
        }
        return this.szerzo + " - " + this.likeok + " - " + this.letrejott + "\n" + serkesztveText + this.tartalom;
    }
}
