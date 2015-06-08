package liquibase.actionlogic.core.db2;

import liquibase.Scope;
import liquibase.action.Action;
import liquibase.action.core.AddColumnsAction;
import liquibase.action.core.ReorganizeTableAction;
import liquibase.actionlogic.ActionResult;
import liquibase.actionlogic.DelegateResult;
import liquibase.actionlogic.core.AddColumnsLogic;
import liquibase.database.Database;
import liquibase.database.core.db2.DB2Database;
import liquibase.exception.ActionPerformException;
import liquibase.structure.ObjectName;

public class AddColumnLogicDB2 extends AddColumnsLogic {
    @Override
    protected Class<? extends Database> getRequiredDatabase() {
        return DB2Database.class;
    }

    @Override
    public ActionResult execute(AddColumnsAction action, Scope scope) throws ActionPerformException {
        return new DelegateResult((DelegateResult) super.execute(action, scope),
                new ReorganizeTableAction(action.tableName)
        );
    }
}