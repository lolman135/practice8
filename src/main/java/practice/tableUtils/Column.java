package practice.tableUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * {@code Column} class represents a database table column with a name and data type.
 *
 * @author Kyrylo Kusovskyi
 */
@Getter
@AllArgsConstructor
public class Column {

    private String name;
    private String dataType;
}
