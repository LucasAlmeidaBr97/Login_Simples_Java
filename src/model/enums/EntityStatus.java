package model.enums;

public enum EntityStatus {
    ACTIVE("Ativa"),
    INACTIVE("Desativada"),
    EXPIRED("Expirada"),
    LOCKED("Bloqueada");

    private final String label;

    EntityStatus(String label) {
        this.label = label;    
    }

    public String getLabel() {
        return label;
    }
    
}
