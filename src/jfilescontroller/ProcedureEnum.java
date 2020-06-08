package jfilescontroller;
/**
 *
 * @author Jose Francisco
 */
public enum ProcedureEnum
{
    INGRESAR_UNIDAD("{ call sp_ingresar_unidad(?,?) }"),
    INGRESAR_DETALLE_UNIDAD("{ call sp_ingresar_detalle_unidad(?,?,?)}");

    private ProcedureEnum(String procedure)
    {
        this.procedure = procedure;
    }

    public String getProcedure()
    {
        return procedure;
    }

    private String procedure;
}