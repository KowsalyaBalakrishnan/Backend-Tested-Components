package excel;

public class ELabelUpload {

    private String bs;
    private String label;

    public ELabelUpload() {
    }

    public ELabelUpload(String bs, String label) {
        this.bs = bs;
        this.label = label;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "ELabelUpload{" +
                "bs='" + bs + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
