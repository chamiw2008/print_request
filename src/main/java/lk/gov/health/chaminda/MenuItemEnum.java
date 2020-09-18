package lk.gov.health.chaminda;

public enum MenuItemEnum {
    
    EVALUATION("Evaluation"),
    CLIENTS("User Functions"),
    PRODUCTS("Administrative"),
    ABOUT_US("Requests"),
    CONTACT("Contact"),
    NONE("");

    private String label;

    private MenuItemEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
