package raven.cell;

/**
 *
 * @author RAVEN
 */
public interface TableActionEvent {

    public void onAdd(int row);

    public void onDelete(int row);

    public void onLess(int row);
}
