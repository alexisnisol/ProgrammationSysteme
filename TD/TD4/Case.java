public class Case {

    private TypeCase type;

    public Case(TypeCase type) {
        this.type = type;
    }

    public TypeCase getType() {
        return type;
    }

    public void setType(TypeCase type) {
        this.type = type;
    }

    public boolean isIntacte() {
        return type == TypeCase.INTACTE;
    }

    public int getValue() {
        return this.type.getFireValue();
    }

    public void incrementValue() {
        this.type = TypeCase.values()[this.type.getFireValue() + 1 % TypeCase.values().length];
    }


    
}

enum TypeCase {
    INTACTE(0),
    FEU_INTENSITE_1(1),
    FEU_INTENSITE_2(2),
    FEU_INTENSITE_3(3),
    BRULEE(5);

    private final int value;

    TypeCase(int value) {
        this.value = value;
    }

    public int getFireValue() {
        return value;
    }
}