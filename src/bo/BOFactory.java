package bo;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BoTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentBOImpl();
            case PROGRAM:
                return new ProgramBOImpl();
            case PROGRAMDATA:
                return new ProgramDataBOImpl();
            default:
                return null;
        }
    }

    public enum BoTypes {
        STUDENT,PROGRAM,PROGRAMDATA
    }
}
